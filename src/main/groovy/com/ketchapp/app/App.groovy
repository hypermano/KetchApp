package com.ketchapp.app

import com.ketchapp.engine.fetcher.DBFetcher
import com.ketchapp.engine.fetcher.UrlFetcher
import com.ketchapp.engine.utils.JsonUtils
import com.ketchapp.pers.dao.MediaDao
import com.ketchapp.types.Artist
import com.ketchapp.types.Release

/**
 * Created by emmanuel on 8/8/2015.
 */
class App {

    private MediaDao dao = new MediaDao()

    private DBFetcher db = new DBFetcher()
    private UrlFetcher web = new UrlFetcher()

    public void run(String query) {
        List<Artist> artists = getArtists(query)
        List<Release> records = getRecords(artists.first())

        println records
    }

    private List<Artist> getArtists(String query) {
        List<Artist> artists = db.searchArtist(query)
        if (!artists) {
            artists = web.searchArtist(query)

            if (!artists?.isEmpty()) {
                dao.setByQuery(query, JsonUtils.toJson(artists))
            }
        }
        artists
    }

    private List<Release> getRecords(Artist artist) {
        List<Release> releases = db.listArtistReleases(artist.id)
        if (!releases) {
            releases = web.listArtistReleases(artist.id)

            if (!releases?.isEmpty()) {
                dao.setById(artist.id, JsonUtils.toJson(releases))
            }
        }
        releases
    }

    public static void main(String[] args) {
        new App().run("Kamelot")
    }

}

