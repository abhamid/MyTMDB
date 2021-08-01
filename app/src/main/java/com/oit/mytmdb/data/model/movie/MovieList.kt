package com.oit.mytmdb.data.model.movie
import com.google.gson.annotations.SerializedName
import com.oit.mytmdb.data.model.movie.Movie

data class MovieList(
    @SerializedName("results")
    val movies: List<Movie>

    )