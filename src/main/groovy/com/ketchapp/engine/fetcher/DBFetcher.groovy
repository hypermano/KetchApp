package com.ketchapp.engine.fetcher

import com.fasterxml.jackson.core.type.TypeReference
import com.ketchapp.engine.utils.JsonUtils
import com.ketchapp.pers.dao.MediaDao
import com.ketchapp.pers.dao.redis.RedisKeyGenerator
import com.ketchapp.types.Artist
import com.ketchapp.types.Release

/**
 * Created by emmanuel on 8/8/2015.
 */
class DBFetcher implements Fetcher {

    private MediaDao dao = new MediaDao();

    @Override
    List<Artist> searchArtist(String query) {
        def res = dao.getByQuery(query)

        if (res) {
            return JsonUtils.toObjectList(res, new TypeReference<List<Artist>>() {})
        }

        return null
    }

    @Override
    def lookup(UrlFetcher.ServiceType type, String id) {
        return null
    }

    @Override
    List<Release> listArtistReleases(String artistId) {
        def res = dao.getById(artistId)

        if (res) {
            return JsonUtils.toObjectList(res, new TypeReference<List<Release>>() {})
        }

        return null
    }
}
