package com.oit.mytmdb.presentation.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oit.mytmdb.data.model.artist.Artist
import com.oit.mytmdb.domain.usecase.GetArtistsUseCase
import com.oit.mytmdb.domain.usecase.UpdateArtistsUseCase

class ArtistsViewModel constructor(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {

    fun getArtists():LiveData<List<Artist>> = liveData {
        val artists:List<Artist> = getArtistsUseCase.execute() ?: emptyList<Artist>()
        emit(artists)
    }

    fun updateArtists(): LiveData<List<Artist>> = liveData {
        val artists:List<Artist> = updateArtistsUseCase.execute() ?: emptyList<Artist>()
        emit(artists)
    }

}