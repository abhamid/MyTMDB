package com.oit.mytmdb.presentation.di.tvshows

import com.oit.mytmdb.domain.usecase.GetTVShowsUseCase
import com.oit.mytmdb.domain.usecase.UpdateTVShowsUseCase
import com.oit.mytmdb.presentation.tvshows.TvShowsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowsModule {

    @TvShowsScope
    @Provides
    fun providesTvShowsViewModelFactory(
        getTVShowsUseCase: GetTVShowsUseCase,
        updateTVShowsUseCase: UpdateTVShowsUseCase
    ) : TvShowsViewModelFactory =
        TvShowsViewModelFactory(getTVShowsUseCase, updateTVShowsUseCase)

}