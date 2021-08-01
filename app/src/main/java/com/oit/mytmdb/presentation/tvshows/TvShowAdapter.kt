package com.oit.mytmdb.presentation.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oit.mytmdb.BuildConfig
import com.oit.mytmdb.R
import com.oit.mytmdb.data.model.tvshow.TVShow
import com.oit.mytmdb.databinding.ListItemBinding

class TvShowAdapter(
    private val tvShowList: List<TVShow>
) : RecyclerView.Adapter<TvShowsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding:ListItemBinding = DataBindingUtil.inflate(
            inflator,
            R.layout.list_item,
            parent,
            false
        )

        return TvShowsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShow = tvShowList.get(position)
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }
}

class TvShowsViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tvShow: TVShow) {
        binding.titleTextView.text = tvShow.name
        binding.descriptionTextView.text = tvShow.overview

        val posterUrl = "${BuildConfig.IMAGE_BASE_URL}${tvShow.posterPath}"
        Glide.with(binding.imageView.context)
            .load(posterUrl)
            .into(binding.imageView)
    }
}