package com.oit.mytmdb.presentation.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oit.mytmdb.R
import com.oit.mytmdb.data.model.movie.Movie
import com.oit.mytmdb.databinding.ActivityMoviesBinding
import com.oit.mytmdb.presentation.di.Injector
import javax.inject.Inject

class MoviesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMoviesBinding

    @Inject
    lateinit var moviewViewModelFactory: MoviesViewModelFactory

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter: MovieAdapter
    private val movies:MutableList<Movie> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)

        (application as Injector).createMovieSubComponent().inject(this)

        moviesViewModel = ViewModelProvider(this, moviewViewModelFactory).get(MoviesViewModel::class.java)

        initMovieRecyclerView()
        displayPopularMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infaltor:MenuInflater = menuInflater
        infaltor.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_update -> {
                updatePopularMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initMovieRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(movies)
        binding.rvMovies.adapter = adapter
    }

    private fun displayPopularMovies() {
        binding.pbMovies.visibility = View.VISIBLE
        moviesViewModel.getMovies().observe(this) {
            refreshMovieList(it)
            binding.pbMovies.visibility = View.GONE
        }
    }

    private fun updatePopularMovies() {
        binding.pbMovies.visibility = View.VISIBLE
        val response:LiveData<List<Movie>> = moviesViewModel.updateMovies()
        response.observe(this) {
            refreshMovieList(it)
            binding.pbMovies.visibility = View.GONE
        }
    }

    private fun refreshMovieList(currentMovies:List<Movie>) {
        movies.clear()
        movies.addAll(currentMovies)
        adapter.notifyDataSetChanged()


        if(currentMovies.isEmpty()) {
            Toast.makeText(this@MoviesActivity, "No Data Available", Toast.LENGTH_SHORT).show()
        }
    }

}