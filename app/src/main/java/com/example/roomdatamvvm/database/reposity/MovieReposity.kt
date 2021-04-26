package com.example.roomdatamvvm.database.reposity

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomdatamvvm.database.BaseDatabase
import com.example.roomdatamvvm.database.dao.MovieDao
import com.example.roomdatamvvm.model.Movie

class MovieReposity(val application: Application) {

    val movieDao: MovieDao

    init {
        val myBaseDatabase = BaseDatabase.getInstance(application)
        movieDao = myBaseDatabase.getMovieDao()
    }

    suspend fun insertMovie(movie: Movie)  = movieDao.insert(movie)

    suspend fun getAllMovie(): MutableList<Movie> = movieDao.getAllMovie()

}