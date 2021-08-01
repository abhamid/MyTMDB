package com.oit.mytmdb.domain.usecase

import com.oit.mytmdb.data.model.artist.Artist
import com.oit.mytmdb.domain.repository.ArtistRepository

class GetArtistsUseCase (private val artistRepository: ArtistRepository) {
    suspend fun execute():List<Artist>? = artistRepository.getArtists()
}