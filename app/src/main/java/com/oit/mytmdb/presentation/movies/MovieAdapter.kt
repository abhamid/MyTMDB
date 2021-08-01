package com.oit.mytmdb.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oit.mytmdb.BuildConfig
import com.oit.mytmdb.R
import com.oit.mytmdb.data.model.movie.Movie
import com.oit.mytmdb.databinding.ListItemBinding

class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val infaltor = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            infaltor,
            R.layout.list_item,
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = movies.get(position)
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MovieViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.titleTextView.text = movie.title
        binding.descriptionTextView.text = movie.overview
        val posterUrl: String = "${BuildConfig.IMAGE_BASE_URL}${movie.posterPath}"
        Glide.with(binding.imageView.context)
            .load(posterUrl)
            .into(binding.imageView)

    }

}