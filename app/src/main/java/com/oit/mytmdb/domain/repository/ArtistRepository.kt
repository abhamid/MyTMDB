package com.oit.mytmdb.domain.repository

import com.oit.mytmdb.data.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?
}