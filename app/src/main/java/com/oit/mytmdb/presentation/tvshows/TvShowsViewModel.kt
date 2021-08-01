package com.oit.mytmdb.presentation.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oit.mytmdb.data.model.tvshow.TVShow
import com.oit.mytmdb.domain.usecase.GetTVShowsUseCase
import com.oit.mytmdb.domain.usecase.UpdateTVShowsUseCase

class TvShowsViewModel constructor(
    private val getTVShowsUseCase: GetTVShowsUseCase,
    private val updateTVShowsUseCase: UpdateTVShowsUseCase
) : ViewModel() {
   fun getTVShows(): LiveData<List<TVShow>> = liveData {
       val tvShows:List<TVShow> = getTVShowsUseCase.execute() ?: emptyList<TVShow>()
       emit(tvShows)
   }

   fun updateTVShows(): LiveData<List<TVShow>> = liveData {
       val tvShows:List<TVShow> = updateTVShowsUseCase.execute() ?: emptyList<TVShow>()
       emit(tvShows)
   }

}