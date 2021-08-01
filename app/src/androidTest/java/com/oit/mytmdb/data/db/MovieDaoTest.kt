package com.oit.mytmdb.data.db

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.oit.mytmdb.data.model.movie.Movie
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.arch.core.executor.testing.InstantTaskExecutorRule


@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDao: MovieDao
    private lateinit var tmdbDatabase: TMDBDatabase

    @Before
    fun setUp() {
        tmdbDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()
        movieDao = tmdbDatabase.movieDao
    }

    @After
    fun tearDown() {
        tmdbDatabase.close()
    }

    @Test
    fun saveMovieTest(): Unit = runBlocking {
        val movies:List<Movie> = listOf(
            Movie(1, "OverView1", "http://movie.com/poster1", "2/10/2002", "Movie1"),
            Movie(2, "OverView2", "http://movie.com/poster2", "2/10/2002", "Movie2")
        )
        movieDao.saveMovies(movies)

        val allMovies:List<Movie> = movieDao.getAllMovies()

        assertThat(allMovies).isEqualTo(movies)
    }

    @Test
    fun deleteMovies():Unit = runBlocking {
        val movies:List<Movie> = listOf(
            Movie(1, "OverView1", "http://movie.com/poster1", "2/10/2002", "Movie1"),
            Movie(2, "OverView2", "http://movie.com/poster2", "2/10/2002", "Movie2")
        )
        movieDao.saveMovies(movies)

        movieDao.deleteAllMovies()

        val allMovies:List<Movie> = movieDao.getAllMovies()

        assertThat(allMovies).isEmpty()


    }
}
