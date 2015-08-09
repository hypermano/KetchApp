package com.ketchapp.pers.dao.redis

import com.ketchapp.pers.dao.KeyGenerator

/**
 * Created by emmanuel on 8/8/2015.
 */
class RedisKeyGenerator implements KeyGenerator {

    @Override
    String id(String id) {
        return id.toLowerCase()
    }

    @Override
    String query(String query) {
        return "~${query.toLowerCase().replaceAll(' ', '_')}"
    }

}
