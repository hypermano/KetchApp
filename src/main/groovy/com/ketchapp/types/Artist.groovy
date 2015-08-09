package com.ketchapp.types

import com.ketchapp.engine.utils.JsonUtils
import groovy.json.JsonBuilder
import groovy.transform.Immutable

/**
 * Created by emmanuel on 8/8/2015.
 */
@Immutable
class Artist {

    enum ArtistType {
        GROUP, PERSON

        private static Map<String, ArtistType> lookupMap = new HashMap<>()

        static {
            values().each {
                lookupMap.put(it.name().toLowerCase(), it)
            }
        }

        static ArtistType lookup(String value) {
            if (value) {
                return lookupMap.get(value.toLowerCase())
            }
        }
    }

    String id
    String name
    ArtistType type

    String toJson() {
        return JsonUtils.toJson(this)
    }

    static Artist fromJson(String json) {
        return JsonUtils.toObject(json, Artist.class)
    }

    public static void main(String[] args) {
        Artist artist = ['id':'1', 'name':'Test', 'type': ArtistType.PERSON]

        def toJson = artist.toJson()

        println toJson

        println Artist.fromJson(toJson)

        println ArtistType.lookup("person")
        println ArtistType.lookup("df")
        println ArtistType.lookup("grouP")
    }

}
