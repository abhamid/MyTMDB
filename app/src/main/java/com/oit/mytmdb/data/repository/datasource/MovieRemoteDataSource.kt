package com.oit.mytmdb.data.repository.datasource

import com.oit.mytmdb.data.api.TMDBService
import com.oit.mytmdb.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}

class MovieRemoteDataSourceImpl  (
    private val tmdbService: TMDBService,
    private val apiKey: String
    ) : MovieRemoteDataSource {

    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}