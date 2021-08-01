package com.oit.mytmdb.presentation.di.movie

import com.oit.mytmdb.presentation.di.artists.ArtistScope
import com.oit.mytmdb.presentation.movies.MoviesActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(activity:MoviesActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }
}