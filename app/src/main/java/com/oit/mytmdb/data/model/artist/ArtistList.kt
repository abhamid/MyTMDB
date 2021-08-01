package com.oit.mytmdb.data.model.artist


import com.google.gson.annotations.SerializedName
import com.oit.mytmdb.data.model.artist.Artist

data class ArtistList(
    @SerializedName("results")
    val artists: List<Artist>

    )