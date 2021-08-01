package com.oit.mytmdb.presentation.di.movie

import com.oit.mytmdb.domain.usecase.GetMoviesUseCase
import com.oit.mytmdb.domain.usecase.UpdateMoviesUseCase
import com.oit.mytmdb.presentation.movies.MoviesViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class MovieModule {

    @MovieScope
    @Provides
    fun providesMoviesViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ) : MoviesViewModelFactory {

        return MoviesViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
    }
}