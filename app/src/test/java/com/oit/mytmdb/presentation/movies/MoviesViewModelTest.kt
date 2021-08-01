package com.oit.mytmdb.presentation.movies

import com.google.common.truth.Truth.assertThat
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.oit.mytmdb.data.model.movie.Movie
import com.oit.mytmdb.data.repository.MovieRepositoryFake
import com.oit.mytmdb.domain.usecase.GetMoviesUseCase
import com.oit.mytmdb.domain.usecase.UpdateMoviesUseCase
import com.oit.mytmdb.getOrAwaitValue
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var updateMoviesUseCase: UpdateMoviesUseCase

    @Before
    fun setUp() {
        val movieRepositoryFake = MovieRepositoryFake()
        getMoviesUseCase = GetMoviesUseCase(movieRepositoryFake)
        updateMoviesUseCase = UpdateMoviesUseCase(movieRepositoryFake)
        moviesViewModel = MoviesViewModel(getMoviesUseCase, updateMoviesUseCase)
    }

    @Test
    fun getMovies_returnsCurrentList() {
        val movies = mutableListOf(
            Movie(1, "Overview1", "posterPath1", "releaseDate1", "title1"),
            Movie(2, "Overview2", "posterPath2", "releaseDate2", "title2"),
            Movie(3, "Overview3", "posterPath3", "releaseDate3", "title3")
        )

        val currentList = moviesViewModel.getMovies().getOrAwaitValue()

        assertThat(currentList).isEqualTo(movies)
    }

    @Test
    fun updateMovies_returnsUpdatedList() {
        val movies = mutableListOf(
            Movie(4, "Overview4", "posterPath4", "releaseDate4", "title4"),
            Movie(5, "Overview5", "posterPath5", "releaseDate5", "title5"),
            Movie(6, "Overview6", "posterPath6", "releaseDate6", "title6")
        )

        val currentList = moviesViewModel.updateMovies().getOrAwaitValue()

        assertThat(currentList).isEqualTo(movies)
    }
}