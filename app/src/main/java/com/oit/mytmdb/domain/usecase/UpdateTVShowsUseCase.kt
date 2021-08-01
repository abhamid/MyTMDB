package com.oit.mytmdb.domain.usecase

import com.oit.mytmdb.data.model.tvshow.TVShow
import com.oit.mytmdb.domain.repository.TVShowRepository

class UpdateTVShowsUseCase (private val tvShowRepository: TVShowRepository) {
    suspend fun execute(): List<TVShow>? = tvShowRepository.updateTVShows()
}