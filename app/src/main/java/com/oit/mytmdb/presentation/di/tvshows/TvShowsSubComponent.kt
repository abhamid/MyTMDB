package com.oit.mytmdb.presentation.di.tvshows

import com.oit.mytmdb.presentation.tvshows.TvShowsActivity
import dagger.Subcomponent

@TvShowsScope
@Subcomponent(modules = [TvShowsModule::class])
interface TvShowsSubComponent {

    fun inject(activity:TvShowsActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowsSubComponent
    }
}