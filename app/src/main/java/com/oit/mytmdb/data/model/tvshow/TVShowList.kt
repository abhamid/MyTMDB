package com.oit.mytmdb.data.model.tvshow


import com.google.gson.annotations.SerializedName
import com.oit.mytmdb.data.model.tvshow.TVShow

data class TVShowList(
    @SerializedName("results")
    val TVShows: List<TVShow>
)