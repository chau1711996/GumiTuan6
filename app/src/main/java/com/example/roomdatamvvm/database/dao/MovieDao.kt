package com.example.roomdatamvvm.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdatamvvm.model.Movie


@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movie: Movie)
    @Query("select*from movie_table")
    suspend fun getAllMovie(): MutableList<Movie>
}