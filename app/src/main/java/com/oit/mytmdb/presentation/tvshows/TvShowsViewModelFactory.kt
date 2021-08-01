package com.oit.mytmdb.presentation.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oit.mytmdb.domain.usecase.GetTVShowsUseCase
import com.oit.mytmdb.domain.usecase.UpdateTVShowsUseCase
import java.lang.IllegalArgumentException

class TvShowsViewModelFactory constructor(
    val getTVShowsUseCase: GetTVShowsUseCase,
    val updateTVShowsUseCase: UpdateTVShowsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvShowsViewModel::class.java)) {
            return TvShowsViewModel(getTVShowsUseCase, updateTVShowsUseCase) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}