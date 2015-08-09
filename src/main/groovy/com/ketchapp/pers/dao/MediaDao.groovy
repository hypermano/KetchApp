package com.ketchapp.pers.dao

import com.ketchapp.pers.dao.redis.RedisDao
import com.ketchapp.pers.dao.redis.RedisKeyGenerator
import redis.clients.jedis.Jedis

/**
 * Created by emmanuel on 8/8/2015.
 */
class MediaDao {

    KeyValueDao dao = new RedisDao()
    KeyGenerator kgen = new RedisKeyGenerator()

    void setById(String id, String value) {
        dao.set(kgen.id(id), value)
    }

    String getById(String id) {
        return dao.get(kgen.id(id))
    }

    void setByQuery(String query, String value) {
        dao.set(kgen.query(query), value)
    }

    String getByQuery(String query) {
        return dao.get(kgen.query(query))
    }


}
