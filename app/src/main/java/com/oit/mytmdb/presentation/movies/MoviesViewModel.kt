package com.oit.mytmdb.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oit.mytmdb.data.model.movie.Movie
import com.oit.mytmdb.domain.usecase.GetMoviesUseCase
import com.oit.mytmdb.domain.usecase.UpdateMoviesUseCase

class MoviesViewModel constructor (
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    fun getMovies():LiveData<List<Movie>> = liveData {
        val movieList:List<Movie> = getMoviesUseCase.execute() ?: emptyList<Movie>()
        emit(movieList)
    }

    fun updateMovies(): LiveData<List<Movie>> = liveData {
        val movieList:List<Movie> = updateMoviesUseCase.execute() ?: emptyList<Movie>()
        emit(movieList)
    }
}