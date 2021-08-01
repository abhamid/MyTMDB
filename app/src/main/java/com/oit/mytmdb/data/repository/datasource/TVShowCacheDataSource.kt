package com.oit.mytmdb.data.repository.datasource

import com.oit.mytmdb.data.model.tvshow.TVShow

interface TVShowCacheDataSource {
    suspend fun getTvShowsFromCache(): List<TVShow>
    suspend fun saveTvShowsToCache(tvShows: List<TVShow>)
}

class TVShowCacheDataSourceImpl: TVShowCacheDataSource {
    private val tvShowList = ArrayList<TVShow>()

    override suspend fun getTvShowsFromCache(): List<TVShow> {
        return tvShowList
    }

    override suspend fun saveTvShowsToCache(tvShows: List<TVShow>) {
        tvShowList.addAll(tvShows)
    }
}