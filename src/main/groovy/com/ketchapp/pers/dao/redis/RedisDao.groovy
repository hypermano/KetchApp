package com.ketchapp.pers.dao.redis

import com.ketchapp.pers.dao.KeyValueDao
import redis.clients.jedis.Jedis

/**
 * Created by emmanuel on 8/8/2015.
 */
class RedisDao implements KeyValueDao {

    private final Jedis jedis = new Jedis("localhost")

    @Override
    void set(String key, String value) {
        jedis.set(key, value)
    }

    @Override
    String get(String key) {
        return jedis.get(key)
    }

}
