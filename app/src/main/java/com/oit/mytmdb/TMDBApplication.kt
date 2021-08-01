package com.oit.mytmdb

import android.app.Application
import com.oit.mytmdb.presentation.di.Injector
import com.oit.mytmdb.presentation.di.artists.ArtistSubComponent
import com.oit.mytmdb.presentation.di.core.*
import com.oit.mytmdb.presentation.di.movie.MovieSubComponent
import com.oit.mytmdb.presentation.di.tvshows.TvShowsSubComponent

class TMDBApplication: Application(), Injector {
   private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netmodule(Netmodule(BuildConfig.BASE_URL))
            .remoteDataSourceModule(RemoteDataSourceModule(BuildConfig.API_KEY))
            .build()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponent.artistSubComponent().create()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createTvShowsSubComponent(): TvShowsSubComponent {
        return appComponent.tvshowsSubComponent().create()
    }
}