package com.oit.mytmdb.presentation.di

import com.oit.mytmdb.presentation.di.artists.ArtistSubComponent
import com.oit.mytmdb.presentation.di.movie.MovieSubComponent
import com.oit.mytmdb.presentation.di.tvshows.TvShowsSubComponent

interface Injector {
    fun createArtistSubComponent(): ArtistSubComponent
    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowsSubComponent(): TvShowsSubComponent
}