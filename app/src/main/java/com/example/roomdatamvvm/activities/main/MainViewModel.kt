package com.example.roomdatamvvm.activities.main

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatamvvm.activities.catelory.CateloryActivity
import com.example.roomdatamvvm.activities.catelory.CateloryViewModel
import com.example.roomdatamvvm.activities.showMovie.ShowMovieActivity
import com.example.roomdatamvvm.utils.Clicks

class MainViewModel(private val application: Application): ViewModel(), Observable {

    @Bindable
    val openCateloryButton = MutableLiveData<String>()

    init {
        openCateloryButton.value = "ADD"
    }

    fun openCateloryActivity(){
        Clicks.callIntent(application, CateloryActivity::class.java)
    }

    fun showMovieActivity(){
        Clicks.callIntent(application, ShowMovieActivity::class.java)
    }

    class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application) as T
            }

            throw IllegalAccessException("Unable construct viewModel")
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}