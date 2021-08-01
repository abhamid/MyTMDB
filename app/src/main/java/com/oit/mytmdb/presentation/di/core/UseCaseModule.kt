package com.oit.mytmdb.presentation.di.core

import com.oit.mytmdb.domain.repository.ArtistRepository
import com.oit.mytmdb.domain.repository.MovieRepository
import com.oit.mytmdb.domain.repository.TVShowRepository
import com.oit.mytmdb.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetArtistsUseCase(artistRepository: ArtistRepository): GetArtistsUseCase {
        return GetArtistsUseCase(artistRepository)
    }

    @Singleton
    @Provides
    fun providesGetMoviesUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun providesGetTVShowsUseCase(tvShowRepository: TVShowRepository) : GetTVShowsUseCase {
        return GetTVShowsUseCase(tvShowRepository)
    }

    @Singleton
    @Provides
    fun providesUpdateArtistsUseCase(artistRepository: ArtistRepository) : UpdateArtistsUseCase {
        return UpdateArtistsUseCase(artistRepository)
    }

    @Singleton
    @Provides
    fun providesUpdateMoviesUseCase(movieRepository: MovieRepository) : UpdateMoviesUseCase {
        return UpdateMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun providesUpdateTVShowsUseCase(tvShowRepository: TVShowRepository): UpdateTVShowsUseCase {
        return UpdateTVShowsUseCase(tvShowRepository)
    }
}