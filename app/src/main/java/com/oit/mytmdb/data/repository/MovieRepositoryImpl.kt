package com.oit.mytmdb.data.repository

import android.util.Log
import com.oit.mytmdb.data.model.movie.Movie
import com.oit.mytmdb.data.model.movie.MovieList
import com.oit.mytmdb.data.repository.datasource.MovieCacheDataSource
import com.oit.mytmdb.data.repository.datasource.MovieLocalDataSource
import com.oit.mytmdb.data.repository.datasource.MovieRemoteDataSource
import com.oit.mytmdb.domain.repository.MovieRepository
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>?  = getMoviesFromCache()

    override suspend fun updateMovies(): List<Movie>? {
        var movieList: List<Movie>? = null
        movieList = getMoviesFromAPI()
        movieList?.let {
            movieLocalDataSource.clearMovies()
            movieLocalDataSource.saveMoviesToDB(it)
            movieCacheDataSource.saveMoviesToCache(it)
        }

        return movieList
    }

    suspend fun getMoviesFromAPI(): List<Movie>? {
        var movieList: List<Movie>? = null

        try {
            val response: Response<MovieList> = movieRemoteDataSource.getMovies()
            val responseBody:MovieList? = response.body()
            responseBody?.let {
                movieList = it.movies
            }
        } catch (ex: Exception) {
            Log.e("MyTag", ex.message.toString())
        }

        return movieList

    }

    suspend fun getMoviesFromDB(): List<Movie>? {
        var movieList: List<Movie>? = null

        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        } catch (ex: Exception) {
            Log.e("MyTag", ex.message.toString())
        }

        if(movieList != null && movieList.size > 0) {
            return movieList
        }

        movieList = getMoviesFromAPI()
        movieList?.let {
            movieLocalDataSource.saveMoviesToDB(movieList)
        }

        return movieList
    }

    suspend fun getMoviesFromCache() : List<Movie>? {
        var movieList: List<Movie>? = null
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (ex: Exception) {
            Log.e("MyTag", ex.message.toString())
        }

        if(movieList != null && movieList.size > 0) {
            return movieList
        } else {
            movieList = getMoviesFromDB()
            movieList?.let {
                movieCacheDataSource.saveMoviesToCache(movieList)
            }

        }

        return movieList
    }
}