package com.oit.mytmdb.presentation.tvshows

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
import com.oit.mytmdb.data.model.tvshow.TVShow
import com.oit.mytmdb.databinding.ActivityTvShowsBinding
import com.oit.mytmdb.presentation.di.Injector
import com.oit.mytmdb.presentation.movies.MoviesViewModel
import javax.inject.Inject

class TvShowsActivity : AppCompatActivity() {
    lateinit var binding: ActivityTvShowsBinding

    private lateinit var adapter: TvShowAdapter
    private val tvShowList:MutableList<TVShow> = mutableListOf()

    @Inject
    lateinit var tvShowsViewModelFactory: TvShowsViewModelFactory

    private lateinit var tvShowsViewModel: TvShowsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_shows)

        (application as Injector).createTvShowsSubComponent().inject(this)

        tvShowsViewModel = ViewModelProvider(this, tvShowsViewModelFactory)
            .get(TvShowsViewModel::class.java)

        initTvShowRecyclerView()
        displayTvShows()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater:MenuInflater = menuInflater
        menuInflater.inflate(
            R.menu.update,
            menu
        )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_update -> {
                updateTvShows()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun initTvShowRecyclerView() {
        binding.rvTvShows.layoutManager = LinearLayoutManager(this)
        adapter = TvShowAdapter(tvShowList)
        binding.rvTvShows.adapter = adapter
    }

    private fun displayTvShows() {
        binding.pbTvShows.visibility = View.VISIBLE
        tvShowsViewModel.getTVShows().observe(this) {
            refreshScreen(it)
            binding.pbTvShows.visibility = View.GONE
        }
    }

    private fun updateTvShows() {
        binding.pbTvShows.visibility = View.VISIBLE
        tvShowsViewModel.updateTVShows().observe(this) {
            refreshScreen(it)
            binding.pbTvShows.visibility = View.GONE
        }
    }

    private fun refreshScreen(currentTvShows:List<TVShow>) {
        tvShowList.clear()
        tvShowList.addAll(currentTvShows)
        adapter.notifyDataSetChanged()

        if(currentTvShows.isEmpty()) {
            Toast.makeText(this@TvShowsActivity, "No Data Available", Toast.LENGTH_SHORT).show()
        }
    }
}