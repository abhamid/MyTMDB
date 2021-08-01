package com.oit.mytmdb.data.repository

import android.util.Log
import com.oit.mytmdb.data.model.tvshow.TVShow
import com.oit.mytmdb.data.model.tvshow.TVShowList
import com.oit.mytmdb.data.repository.datasource.TVShowCacheDataSource
import com.oit.mytmdb.data.repository.datasource.TVShowLocalDataSource
import com.oit.mytmdb.data.repository.datasource.TVShowRemoteDataSource
import com.oit.mytmdb.domain.repository.TVShowRepository
import retrofit2.Response

class TVShowRepositoryImpl (
    private val tvShowRemoteDataSource: TVShowRemoteDataSource,
    private val tvShowLocalDataSource: TVShowLocalDataSource,
    private val tvShowCacheDataSource: TVShowCacheDataSource
        ) : TVShowRepository {
    override suspend fun getTVShows(): List<TVShow>? = getTvShowsFromCache()


    override suspend fun updateTVShows(): List<TVShow>? {
        var tvShows:List<TVShow>? = null

        tvShows = getTvShowsFromRemoteAPI()
        tvShows?.let {
            tvShowLocalDataSource.clearAllTvShows()
            tvShowLocalDataSource.saveTvShowsToDB(it)
            tvShowCacheDataSource.saveTvShowsToCache(it)
        }

        return tvShows

    }

    suspend fun getTvShowsFromRemoteAPI():List<TVShow>? {
        var tvShows:List<TVShow>? = null
        try {
            val response: Response<TVShowList> = tvShowRemoteDataSource.getTvShows()
            val tvShowList:TVShowList? = response.body()
            tvShowList?.let {
                tvShows = it.TVShows
            }
        } catch (ex:Exception){
            Log.e("MyTag", ex.message.toString())
        }
        return tvShows
    }

    suspend fun getTvShowsFromLocalDB():List<TVShow>? {
        var tvShows:List<TVShow>? = null
        try {
            tvShows = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (ex:Exception){
            Log.e("MyTag", ex.message.toString())
        }

        if(tvShows != null && !tvShows.isEmpty()) {
            return tvShows
        }

        tvShows = getTvShowsFromRemoteAPI()
        tvShows?.let {
            tvShowLocalDataSource.saveTvShowsToDB(tvShows)
        }

        return tvShows
    }

    suspend fun getTvShowsFromCache():List<TVShow>? {
        var tvShows:List<TVShow>? = null
        try {
            tvShows = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (ex:Exception){
            Log.e("MyTag", ex.message.toString())
        }

        if(tvShows != null && !tvShows.isEmpty()){
            return tvShows
        }

        tvShows = getTvShowsFromLocalDB()
        tvShows?.let {
            tvShowCacheDataSource.saveTvShowsToCache(it)
        }

        return tvShows
    }

}