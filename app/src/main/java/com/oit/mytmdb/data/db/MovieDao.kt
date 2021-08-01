package com.oit.mytmdb.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oit.mytmdb.data.model.movie.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>): List<Long>

    @Query("DELETE FROM popular_movies")
    suspend fun deleteAllMovies(): Int

    @Query("SELECT * FROM popular_movies")
    suspend fun getAllMovies(): List<Movie>

}