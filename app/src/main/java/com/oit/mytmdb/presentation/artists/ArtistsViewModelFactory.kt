package com.oit.mytmdb.presentation.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oit.mytmdb.domain.usecase.GetArtistsUseCase
import com.oit.mytmdb.domain.usecase.UpdateArtistsUseCase
import java.lang.IllegalArgumentException

class ArtistsViewModelFactory(
    val getArtistsUseCase: GetArtistsUseCase,
    val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArtistsViewModel::class.java)) {
            return ArtistsViewModel(getArtistsUseCase, updateArtistsUseCase) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}