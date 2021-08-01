package com.oit.mytmdb.presentation.di.core

import com.oit.mytmdb.data.repository.datasource.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataSourceModule {

    @Singleton
    @Provides
    fun providesMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()

    }

    @Singleton
    @Provides
    fun providesArtistCacheDataSource() : ArtistCacheDataSource {
        return ArtistCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun providesTVShowCacheDataSource(): TVShowCacheDataSource {
        return TVShowCacheDataSourceImpl()
    }
}