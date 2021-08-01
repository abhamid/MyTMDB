package com.oit.mytmdb.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oit.mytmdb.data.model.tvshow.TVShow

@Dao
interface TVShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTVShows(shows:List<TVShow>): List<Long>

    @Query("DELETE FROM popular_tv_shows")
    suspend fun deleteAllTVShows(): Int

    @Query("SELECT * FROM popular_tv_shows")
    fun getAllTVShows(): List<TVShow>
}