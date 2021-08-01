package com.oit.mytmdb.data.repository

import com.oit.mytmdb.data.model.movie.Movie
import com.oit.mytmdb.domain.repository.MovieRepository

class MovieRepositoryFake : MovieRepository {
    private lateinit var movies: MutableList<Movie>

    init {
        movies = mutableListOf(
            Movie(1, "Overview1", "posterPath1", "releaseDate1", "title1"),
            Movie(2, "Overview2", "posterPath2", "releaseDate2", "title2"),
            Movie(3, "Overview3", "posterPath3", "releaseDate3", "title3")
        )
    }

    override suspend fun getMovies(): List<Movie>? {
        return movies
    }

    override suspend fun updateMovies(): List<Movie>? {
        val updatedMovieList = mutableListOf(
            Movie(4, "Overview4", "posterPath4", "releaseDate4", "title4"),
            Movie(5, "Overview5", "posterPath5", "releaseDate5", "title5"),
            Movie(6, "Overview6", "posterPath6", "releaseDate6", "title6")
        )

        movies.clear()
        movies.addAll(updatedMovieList)
        return movies
    }
}