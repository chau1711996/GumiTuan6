package com.example.roomdatamvvm.activities.movie

import android.app.Application
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.roomdatamvvm.activities.catelory.CateloryViewModel
import com.example.roomdatamvvm.activities.main.MainActivity
import com.example.roomdatamvvm.activities.showMovie.ShowMovieActivity
import com.example.roomdatamvvm.database.reposity.CateloryReposity
import com.example.roomdatamvvm.database.reposity.MovieReposity
import com.example.roomdatamvvm.model.CateloryMovie
import com.example.roomdatamvvm.model.Movie
import com.example.roomdatamvvm.utils.Clicks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddMovieViewModel(private val application: Application) : ViewModel(), Observable {

    val reposity = MovieReposity(application)
    val reposityCategory = CateloryReposity(application)
    var checkClick: MutableLiveData<Boolean?> = MutableLiveData(null)

    val dataMovie: MutableLiveData<MutableList<Movie>?> = MutableLiveData(null)
    val dataCatelory: MutableLiveData<MutableList<String?>> = MutableLiveData(null)
    val isSelectSpiner: MutableLiveData<String?> = MutableLiveData(null)

    companion object{
        @JvmStatic
        @BindingAdapter("selectItemChoose")
        fun clickSpinner(spinner: AppCompatSpinner, result: MutableLiveData<String>){
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    result.postValue(parent?.getItemAtPosition(position).toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }

    @Bindable
    val addMovieButton = MutableLiveData<String>()

    @Bindable
    val showActivityButton = MutableLiveData<String>()

    @Bindable
    val nameCatelory = MutableLiveData<String>()

    @Bindable
    val nameMovie = MutableLiveData<String>()

    init {
        addMovieButton.value = "ADD"
        showActivityButton.value = "SHOW"
    }


    fun addMovie(nameCatelory: String) {
        if (nameMovie.value.isNullOrEmpty()) {
            Toast.makeText(application, "Enter Movie Name", Toast.LENGTH_SHORT).show()
        } else {
            insertMovie(Movie(0, nameMovie.value.toString(), nameCatelory))
        }
        nameMovie.value = null
    }

    fun clickAddMovie() {
        checkClick.postValue(true)
    }

    fun showListSpinner(): LiveData<MutableList<String>> = reposityCategory.getAllNameCatelory()

    fun openMainActivity() {
        Clicks.callIntent(application, MainActivity::class.java)
    }

    fun insertMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        reposity.insertMovie(movie)
    }

    fun getAllMovie() = viewModelScope.launch(Dispatchers.IO) {
        dataMovie.postValue(reposity.getAllMovie())
    }

    class AddMovieViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddMovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddMovieViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}