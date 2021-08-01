package com.oit.mytmdb.domain.repository

import com.oit.mytmdb.data.model.tvshow.TVShow

interface TVShowRepository {
    suspend fun getTVShows(): List<TVShow>?
    suspend fun updateTVShows(): List<TVShow>?
}