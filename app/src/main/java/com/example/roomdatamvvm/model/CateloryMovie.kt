package com.example.roomdatamvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catelory_movie_table")
data class CateloryMovie(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "catelory_name")
    var name: String = "",
)