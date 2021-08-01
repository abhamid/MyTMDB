package com.oit.mytmdb.presentation.di.core

import android.content.Context
import com.oit.mytmdb.presentation.di.artists.ArtistSubComponent
import com.oit.mytmdb.presentation.di.movie.MovieSubComponent
import com.oit.mytmdb.presentation.di.tvshows.TvShowsSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [
    ArtistSubComponent::class,
    MovieSubComponent::class,
    TvShowsSubComponent::class
])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesApplicationContext() : Context {
        return context.applicationContext
    }
}