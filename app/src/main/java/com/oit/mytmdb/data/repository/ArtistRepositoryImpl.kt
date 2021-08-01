package com.oit.mytmdb.data.repository

import android.util.Log
import com.oit.mytmdb.data.model.artist.Artist
import com.oit.mytmdb.data.model.artist.ArtistList
import com.oit.mytmdb.data.repository.datasource.ArtistCacheDataSource
import com.oit.mytmdb.data.repository.datasource.ArtistLocalDataSource
import com.oit.mytmdb.data.repository.datasource.ArtistRemoteDataSource
import com.oit.mytmdb.domain.repository.ArtistRepository
import retrofit2.Response

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {


    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        var artists:List<Artist>? = null
        artists = getArtistFromAPI()
        artists?.let {
            artistLocalDataSource.clearArtists()
            artistLocalDataSource.saveArtistToDB(it)
            artistCacheDataSource.saveArtistsToCache(it)
        }

        return artists
    }

    suspend fun getArtistFromAPI(): List<Artist>? {
        var artists:List<Artist>? = null
        try {
            val response: Response<ArtistList> = artistRemoteDataSource.getArtists()
            val responseBody:ArtistList? = response.body()
            responseBody?.let {
                artists = it.artists
            }
        } catch (ex: Exception) {
            Log.e("MyTag", ex.message.toString())
        }

        return artists

    }

    suspend fun getArtistsFromLocalDB():List<Artist>? {
        var artists:List<Artist>? = null
        try {
            artists = artistLocalDataSource.getArtistFromDB()
        } catch (ex: Exception) {
            Log.e("MyTag", ex.message.toString())
        }
        if(artists != null && artists.size > 0) {
            return artists
        }
        artists = getArtistFromAPI()
        artists?.let {
            artistLocalDataSource.saveArtistToDB(it)
        }

        return artists
    }

    suspend fun getArtistsFromCache():List<Artist>? {
        var artists:List<Artist>? = null
        try {
            artists = artistCacheDataSource.getArtistsFromCache()
        } catch (ex: Exception) {
            Log.e("MyTag", ex.message.toString())
        }

        if(artists != null && artists.size > 0) {
            return artists
        }

        artists = getArtistsFromLocalDB()
        artists?.let {
            artistCacheDataSource.saveArtistsToCache(artists)
        }

        return artists
    }
}