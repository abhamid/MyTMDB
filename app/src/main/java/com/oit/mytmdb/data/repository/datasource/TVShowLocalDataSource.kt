package com.oit.mytmdb.data.repository.datasource

import com.oit.mytmdb.data.db.TVShowDao
import com.oit.mytmdb.data.model.tvshow.TVShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface TVShowLocalDataSource {
    suspend fun getTvShowsFromDB(): List<TVShow>
    suspend fun saveTvShowsToDB(tvShows:List<TVShow>)
    suspend fun clearAllTvShows()
}

class TVShowLocalDataSourceImpl(
    private val tvShowDao: TVShowDao
) : TVShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TVShow> = tvShowDao.getAllTVShows()

    override suspend fun saveTvShowsToDB(tvShows: List<TVShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTVShows(tvShows)
        }
    }

    override suspend fun clearAllTvShows() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTVShows()
        }
    }
}