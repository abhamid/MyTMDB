package com.oit.mytmdb.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oit.mytmdb.data.model.artist.Artist
import com.oit.mytmdb.data.model.movie.Movie
import com.oit.mytmdb.data.model.tvshow.TVShow

@Database(entities = [Artist::class, Movie::class, TVShow::class], version = 1, exportSchema = false)
abstract class TMDBDatabase : RoomDatabase() {
    abstract val artistDao: ArtistDao
    abstract val movieDao: MovieDao
    abstract val tvShowDao: TVShowDao
}