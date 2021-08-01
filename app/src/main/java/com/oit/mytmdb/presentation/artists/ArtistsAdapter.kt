package com.oit.mytmdb.presentation.artists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oit.mytmdb.BuildConfig
import com.oit.mytmdb.R
import com.oit.mytmdb.data.model.artist.Artist
import com.oit.mytmdb.databinding.ListItemBinding

class ArtistsAdapter(
    private val artists: List<Artist>
) : RecyclerView.Adapter<ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            inflator,
            R.layout.list_item,
            parent,
            false
        )

        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        if (artists.size > position) holder.bind(artists.get(position))
    }

    override fun getItemCount(): Int {
        return artists.size
    }
} 

class ArtistViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(artist: Artist) {
        binding.titleTextView.text = artist.name
        binding.descriptionTextView.text = "Popularity: ${artist.popularity}"
        val posterImageUrl = "${BuildConfig.IMAGE_BASE_URL}${artist.profilePath}"
        Glide.with(binding.imageView.context)
            .load(posterImageUrl)
            .into(binding.imageView)
    }
}