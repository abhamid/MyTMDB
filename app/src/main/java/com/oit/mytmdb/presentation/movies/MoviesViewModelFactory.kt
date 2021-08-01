package com.oit.mytmdb.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oit.mytmdb.domain.usecase.GetMoviesUseCase
import com.oit.mytmdb.domain.usecase.UpdateMoviesUseCase
import java.lang.IllegalArgumentException

class MoviesViewModelFactory(
    val getMoviesUseCase: GetMoviesUseCase,
    val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(getMoviesUseCase, updateMoviesUseCase) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}