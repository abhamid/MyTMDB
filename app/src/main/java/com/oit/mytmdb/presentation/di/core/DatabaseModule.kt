package com.oit.mytmdb.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.oit.mytmdb.data.db.ArtistDao
import com.oit.mytmdb.data.db.MovieDao
import com.oit.mytmdb.data.db.TMDBDatabase
import com.oit.mytmdb.data.db.TVShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideTBDBDataBase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclient").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDao {
        return tmdbDatabase.movieDao
    }

    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDao {
        return tmdbDatabase.artistDao
    }

    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase): TVShowDao {
        return tmdbDatabase.tvShowDao
    }

}