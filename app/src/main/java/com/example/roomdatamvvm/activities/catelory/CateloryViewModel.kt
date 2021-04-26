package com.example.roomdatamvvm.activities.catelory

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.roomdatamvvm.activities.movie.AddMovieActivity
import com.example.roomdatamvvm.database.reposity.CateloryReposity
import com.example.roomdatamvvm.model.CateloryMovie
import com.example.roomdatamvvm.utils.Clicks
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CateloryViewModel(private val application: Application) : ViewModel(), Observable {

    private val reposity = CateloryReposity(application)

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val insertButton = MutableLiveData<String>()

    @Bindable
    val openAddMovieButton = MutableLiveData<String>()

    init {
        insertButton.value = "ADD"
        openAddMovieButton.value = "ADD MOVIE"
    }

    fun openAddMovie(){
        Clicks.callIntent(application, AddMovieActivity::class.java)
    }

    fun insert() {
        if (inputName.value.isNullOrEmpty()) {
            Toast.makeText(application, "Enter MovieCatelory", Toast.LENGTH_SHORT).show()
        } else {
            inputName.value.let {
                insertCatelory(CateloryMovie(0, it.toString()))
            }
        }

        inputName.value = null
    }

    fun insertCatelory(cateloryMovie: CateloryMovie) = viewModelScope.launch {
        reposity.insertCatelory(cateloryMovie)
    }

    fun updateCatelory(cateloryMovie: CateloryMovie) = viewModelScope.launch {
        reposity.updateCatelory(cateloryMovie)
    }

    fun deleteCatelory(cateloryMovie: CateloryMovie) = viewModelScope.launch {
        reposity.deleteCatelory(cateloryMovie)
    }

    fun getAllCatelory(): LiveData<MutableList<CateloryMovie>> = reposity.getAllCatelory()

    class CateloryViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CateloryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CateloryViewModel(application) as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
