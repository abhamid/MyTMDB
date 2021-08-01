package com.oit.mytmdb.presentation.di.core

import com.oit.mytmdb.presentation.di.artists.ArtistSubComponent
import com.oit.mytmdb.presentation.di.movie.MovieSubComponent
import com.oit.mytmdb.presentation.di.tvshows.TvShowsSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        Netmodule::class,
        DatabaseModule::class,
        RemoteDataSourceModule::class,
        LocalDataSourceModule::class,
        CacheDataSourceModule::class,
        RepositoryModule::class,
        UseCaseModule::class,

    ])
interface AppComponent {
    fun artistSubComponent(): ArtistSubComponent.Factory
    fun movieSubComponent(): MovieSubComponent.Factory
    fun tvshowsSubComponent(): TvShowsSubComponent.Factory
}