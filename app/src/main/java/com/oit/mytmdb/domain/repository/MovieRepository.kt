package com.oit.mytmdb.domain.repository

import com.oit.mytmdb.data.model.movie.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?
}