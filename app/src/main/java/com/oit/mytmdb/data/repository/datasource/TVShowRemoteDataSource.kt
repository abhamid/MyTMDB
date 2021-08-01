package com.oit.mytmdb.data.repository.datasource

import com.oit.mytmdb.data.api.TMDBService
import com.oit.mytmdb.data.model.tvshow.TVShowList
import retrofit2.Response

interface TVShowRemoteDataSource {
    suspend fun getTvShows(): Response<TVShowList>
}

class TVShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : TVShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TVShowList> = tmdbService.getPopularTVShows(apiKey)
}