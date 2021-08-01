package com.oit.mytmdb.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oit.mytmdb.data.model.artist.Artist

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists:List<Artist>): List<Long>

    @Query("DELETE FROM popular_artists")
    suspend fun deleteAllArtists(): Int

    @Query("SELECT * FROM popular_artists")
    fun getAllArtists(): List<Artist>


}