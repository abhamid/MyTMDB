package com.oit.mytmdb.presentation.artists

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oit.mytmdb.R
import com.oit.mytmdb.TMDBApplication
import com.oit.mytmdb.data.model.artist.Artist
import com.oit.mytmdb.databinding.ActivityArtistsBinding
import com.oit.mytmdb.presentation.di.Injector
import javax.inject.Inject

class ArtistsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtistsBinding

    @Inject
    lateinit var artistsViewModelFactory: ArtistsViewModelFactory

    private lateinit var artistsViewModel: ArtistsViewModel
    private lateinit var adapter: ArtistsAdapter

    private val artistList:MutableList<Artist> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artists)

        (application as Injector).createArtistSubComponent().inject(this)

        artistsViewModel = ViewModelProvider(this, artistsViewModelFactory).get(ArtistsViewModel::class.java)

        initArtistRecyclerView()
        displayPopularArtists()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(
            R.menu.update,
            menu
        )

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_update -> {
                updatePopularArtists()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

    private fun initArtistRecyclerView() {
        binding.recyclerViewArtists.layoutManager = LinearLayoutManager(this)
        adapter = ArtistsAdapter(artistList)
        binding.recyclerViewArtists.adapter = adapter

    }

    private fun displayPopularArtists() {
        binding.progressBarArtists.visibility = View.VISIBLE
        artistsViewModel.getArtists().observe(this) {
            refreshScreen(it)
            binding.progressBarArtists.visibility = View.GONE
        }
    }

    private fun updatePopularArtists() {
        binding.progressBarArtists.visibility = View.VISIBLE
        artistsViewModel.updateArtists().observe(this) {
            refreshScreen(it)
            binding.progressBarArtists.visibility = View.GONE
        }
    }


    private fun refreshScreen(currentArtistList:List<Artist>) {
        artistList.clear()
        artistList.addAll(currentArtistList)
        adapter.notifyDataSetChanged()

        if(artistList.isEmpty()) {
            Toast.makeText(this@ArtistsActivity, "No Data Available", Toast.LENGTH_SHORT).show()
        }
    }

}