package com.example.roomdatamvvm.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.roomdatamvvm.model.CateloryMovie

@Dao
interface CateloryDao {
    @Insert
    suspend fun insertCatelory(cateloryMovie: CateloryMovie)

    @Update
    suspend fun updateCatelory(cateloryMovie: CateloryMovie)

    @Delete
    suspend fun deleteCatelory(cateloryMovie: CateloryMovie)

    @Query("select * from catelory_movie_table")
    fun getAllCatelory(): LiveData<MutableList<CateloryMovie>>

    @Query("select catelory_name from catelory_movie_table")
    fun getAllNameCatelory(): LiveData<MutableList<String>>
}