package com.example.roomdatamvvm.activities.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatamvvm.R
import com.example.roomdatamvvm.databinding.ActivityAddMovieBinding
import com.example.roomdatamvvm.model.Movie

class AddMovieActivity : AppCompatActivity() {

    private val addMovieViewModel: AddMovieViewModel by lazy {
        ViewModelProvider(
            this,
            AddMovieViewModel.AddMovieViewModelFactory(this.application)
        )[AddMovieViewModel::class.java]
    }

    private var binding: ActivityAddMovieBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_movie)
        binding?.lifecycleOwner = this
        binding?.addMovieViewModel = addMovieViewModel
        addMovieViewModel.showListSpinner().observe(this, Observer {
            binding?.spinner?.run {
                it?.let {
                    adapter = ArrayAdapter(this@AddMovieActivity, android.R.layout.simple_list_item_1, it)
                }
            }
        })
        addMovieViewModel.checkClick.observe(this, Observer {
            it?.let {
                addMovieViewModel.addMovie(binding?.spinner?.selectedItem.toString())
            }
        })
    }
}