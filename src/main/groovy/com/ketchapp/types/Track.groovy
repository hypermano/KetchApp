package com.ketchapp.types

import com.ketchapp.engine.utils.JsonUtils
import groovy.transform.Immutable

/**
 * Created by emmanuel on 8/8/2015.
 */
@Immutable
class Track {

    String name
    int number
    int length

    String toJson() {
        return JsonUtils.toJson(this)
    }

    static Track fromJson(String json) {
        return JsonUtils.toObject(json, Track.class)
    }

    public static void main(String[] args) {
        def m = ['name':'111', 'number': 12, 'length':123]
        Track t = m as Track
        println t
        println new Track(m)
    }

}
