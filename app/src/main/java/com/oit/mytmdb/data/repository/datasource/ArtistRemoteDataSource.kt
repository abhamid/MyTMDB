package com.oit.mytmdb.data.repository.datasource

import com.oit.mytmdb.data.api.TMDBService
import com.oit.mytmdb.data.model.artist.Artist
import com.oit.mytmdb.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
}

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey:String
) : ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistList> = tmdbService.getPopularArtists(apiKey)

}