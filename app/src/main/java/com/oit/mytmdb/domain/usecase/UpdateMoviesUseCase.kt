package com.oit.mytmdb.domain.usecase

import com.oit.mytmdb.data.model.movie.Movie
import com.oit.mytmdb.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.updateMovies()
}