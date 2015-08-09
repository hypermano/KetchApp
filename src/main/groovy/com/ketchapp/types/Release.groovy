package com.ketchapp.types

import com.ketchapp.engine.utils.JsonUtils
import groovy.transform.Immutable

/**
 * Created by emmanuel on 8/8/2015.
 */
@Immutable
class Release {

    String id
    String name
    Date date
    List<Track> tracks

    String toJson() {
        return JsonUtils.toJson(this)
    }

    static Release fromJson(String json) {
        return JsonUtils.toObject(json, Release.class)
    }

}