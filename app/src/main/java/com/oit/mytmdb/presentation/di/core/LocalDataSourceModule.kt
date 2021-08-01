package com.oit.mytmdb.presentation.di.core

import com.oit.mytmdb.data.db.ArtistDao
import com.oit.mytmdb.data.db.MovieDao
import com.oit.mytmdb.data.db.TVShowDao
import com.oit.mytmdb.data.repository.datasource.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(
        movieDao: MovieDao
    ): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(
        artistDao: ArtistDao
    ) : ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDao)
    }

    @Singleton
    @Provides
    fun provideTVShowLocalDataSource(
        tvShowDao: TVShowDao
    ) : TVShowLocalDataSource {
        return TVShowLocalDataSourceImpl(tvShowDao)
    }
}