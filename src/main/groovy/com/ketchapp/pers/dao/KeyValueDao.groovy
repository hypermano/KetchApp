package com.ketchapp.pers.dao

/**
 * Created by emmanuel on 8/8/2015.
 */
interface KeyValueDao {

    void set(String key, String value)

    String get(String key)
}