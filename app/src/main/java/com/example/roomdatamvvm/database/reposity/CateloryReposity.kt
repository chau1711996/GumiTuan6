package com.example.roomdatamvvm.database.reposity

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdatamvvm.database.BaseDatabase
import com.example.roomdatamvvm.database.dao.CateloryDao
import com.example.roomdatamvvm.model.CateloryMovie

class CateloryReposity(private val application: Application) {

    val cateloryDao: CateloryDao

    init {
        val myBaseDatabase = BaseDatabase.getInstance(application)
        cateloryDao = myBaseDatabase.getCateloryDao()
    }

    suspend fun insertCatelory(catelory: CateloryMovie) = cateloryDao.insertCatelory(catelory)
    suspend fun updateCatelory(catelory: CateloryMovie) = cateloryDao.updateCatelory(catelory)
    suspend fun deleteCatelory(catelory: CateloryMovie) = cateloryDao.deleteCatelory(catelory)

    fun getAllCatelory(): LiveData<MutableList<CateloryMovie>> = cateloryDao.getAllCatelory()

    fun getAllNameCatelory(): LiveData<MutableList<String>> = cateloryDao.getAllNameCatelory()
}