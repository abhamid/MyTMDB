package com.oit.mytmdb.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.oit.mytmdb.R
import com.oit.mytmdb.databinding.ActivityHomeBinding
import com.oit.mytmdb.presentation.artists.ArtistsActivity
import com.oit.mytmdb.presentation.movies.MoviesActivity
import com.oit.mytmdb.presentation.tvshows.TvShowsActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.homeActivity = this
        binding.lifecycleOwner = this
    }

    fun showMovies() {
        val intent:Intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)
    }

    fun showTvShows() {
        val intent:Intent = Intent(this, TvShowsActivity::class.java )
        startActivity(intent)
    }

    fun showArtists() {
        val intent:Intent = Intent(this, ArtistsActivity::class.java )
        startActivity(intent)
    }
}