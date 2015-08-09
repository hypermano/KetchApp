package com.ketchapp.engine.fetcher

import com.ketchapp.types.Artist
import com.ketchapp.types.Release

/**
 * Created by emmanuel on 8/8/2015.
 */
interface Fetcher {

    List<Artist> searchArtist(String query)

    def lookup(UrlFetcher.ServiceType type, String id)

    List<Release> listArtistReleases(String artistId)
}