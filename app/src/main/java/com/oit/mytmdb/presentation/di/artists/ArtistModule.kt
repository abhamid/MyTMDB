package com.oit.mytmdb.presentation.di.artists

import com.oit.mytmdb.domain.usecase.GetArtistsUseCase
import com.oit.mytmdb.domain.usecase.UpdateArtistsUseCase
import com.oit.mytmdb.presentation.artists.ArtistsViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistsViewModelFactory {
        return ArtistsViewModelFactory(getArtistsUseCase, updateArtistsUseCase)
    }
}