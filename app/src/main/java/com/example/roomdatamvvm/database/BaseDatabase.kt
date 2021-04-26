package com.example.roomdatamvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatamvvm.database.dao.CateloryDao
import com.example.roomdatamvvm.database.dao.MovieDao
import com.example.roomdatamvvm.model.CateloryMovie
import com.example.roomdatamvvm.model.Movie

@Database(entities = arrayOf(Movie::class, CateloryMovie::class), version = 3, exportSchema = false)
abstract class BaseDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getCateloryDao(): CateloryDao

    companion object {
        @Volatile
        private var INSTANCE: BaseDatabase? = null

        fun getInstance(context: Context): BaseDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, BaseDatabase::class.java, "BaseDatabase")
                    .build()
            }
            return INSTANCE!!
        }
    }
}