package com.ketchapp.engine.fetcher

import com.ketchapp.engine.utils.DateUtils
import com.ketchapp.types.Artist
import com.ketchapp.types.Release
import com.ketchapp.types.Track
import groovy.util.slurpersupport.GPathResult

import static UrlFetcher.ServiceType.*

/**
 * Created by emmanuel on 8/6/2015.
 */
class UrlFetcher implements Fetcher {

    static {
        String.metaClass.encodeUrl = {
            URLEncoder.encode(delegate as String, "UTF-8")
        }
    }

    // TODO - get rid of enum, its method are too specific
    enum ServiceType {
        ARTIST("artist"),
        RELEASE("release")

        private String type

        ServiceType(String type) {
            this.type = type
        }

        String query(String query) {
            return type + "?query=$query&fmt=xml"
        }

        String forArtist(String artist) {
            return type + "?artist=$artist&inc=recordings&fmt=xml"
        }

        String lookup(String id) {
            return type + "/$id?inc=recordings&fmt=xml"
        }
    }

    private static final String DOMAIN = "http://musicbrainz.org/"
    private static final String SERVICE = "ws/2/"

    List<Artist> searchArtist(String query) {
        def url = DOMAIN + SERVICE + ARTIST.query(query.encodeUrl())

        def metadata = parseUrlContents(url)

        def res = []
        metadata['artist-list'].artist.find {
            it['@ext:score'].toInteger() > 75
        }.each {
            res.add(new Artist([
                    'id': it.@id,
                    'name': it.name,
                    'type': Artist.ArtistType.lookup("${it.@type}")
            ]))
            println "${it['@ext:score']} : ${it.name} [${it.@id}]"
        }

        return res
    }

    def lookup(ServiceType type, String id) {
        def url = DOMAIN + SERVICE + type.lookup(id)
        def metadata = parseUrlContents(url)

        println metadata
    }

    List<Release> listArtistReleases(String artistId) {
        def url = DOMAIN + SERVICE + RELEASE.forArtist(artistId.encodeUrl())

        def metadata = parseUrlContents(url)

        def res = []
        metadata['release-list'].release.each {
            def tracks = it['medium-list'].medium['track-list'].track.collect {
                println "\t$it.recording.title"
                new Track([
                        'name': it.recording.title,
                        'number': "${it.number}".toInteger(),
                        'length': "${it.length}".toInteger()
                ])
            }

            res.add(new Release([
                    'id': it.@id,
                    'name': it.title,
                    'date': DateUtils.parse("${it.date}"),
                    'tracks' : tracks
            ]))

            println "${it.title} [${it.status}]"
        }

        return res
    }

    private GPathResult parseUrlContents(String url) {
        println url
        def www = new URL(url).getText()
        try {
            return new XmlSlurper().parseText(www)
        } catch (Exception e) {
            e.printStackTrace()
            println www
        }

        return null
    }

    static void main(String[] args) {
        def u = new UrlFetcher()
//        List<Artist> artists = u.searchArtist("Sonata Arctica")
//
//        artists.each {
//            println it.toJson()
//        }

        List<Release> releases = u.listArtistReleases("319b1175-ced9-438f-986b-9239c3edd92d")

        releases.each {
            println it.toJson()
        }
    }

}