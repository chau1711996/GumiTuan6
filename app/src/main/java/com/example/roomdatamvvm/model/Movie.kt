package com.example.roomdatamvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_table")
data class Movie(@PrimaryKey(autoGenerate = true) val id: Int = 0,
            @ColumnInfo(name = "movie_name")val name: String,
            @ColumnInfo(name = "name_catelory") val nameCatelory: String) {
}