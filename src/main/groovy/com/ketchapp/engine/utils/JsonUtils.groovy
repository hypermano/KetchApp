package com.ketchapp.engine.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Created by emmanuel on 8/8/2015.
 */
class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper()

    static String toJson(Object o) {
        return MAPPER.writeValueAsString(o)
    }

    public static <T> T toObject(String str, Class<T> clazz) {
        return MAPPER.readValue(str, clazz)
    }

    public static <T> T toObjectList(String str, TypeReference<T> typeRef) {
        return MAPPER.readValue(str, typeRef)
    }

    public static void main(String[] args) {
        println "!"

        println new ObjectMapper().writeValueAsString(['a':'b'])
    }

}
