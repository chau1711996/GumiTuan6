package com.example.roomdatamvvm.activities.showMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatamvvm.R
import com.example.roomdatamvvm.activities.movie.AddMovieViewModel
import com.example.roomdatamvvm.databinding.ActivityShowMovieBinding
import com.example.roomdatamvvm.model.Movie

class ShowMovieActivity : AppCompatActivity() {
    private val addMovieViewModel: AddMovieViewModel by lazy {
        ViewModelProvider(
            this,
            AddMovieViewModel.AddMovieViewModelFactory(this.application)
        )[AddMovieViewModel::class.java]
    }
    private var binding: ActivityShowMovieBinding? = null
    private var listSpinner = mutableListOf<String>()
    var listFilter = mutableListOf<Movie>()
    private var listShowMovie = mutableListOf<Movie>()
    private var itemMovieAdapter = AdapterShowMovie()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_movie)
        binding?.lifecycleOwner = this
        binding?.addViewModel = addMovieViewModel

        binding?.listShowMovie?.run {
            adapter = itemMovieAdapter
            layoutManager = LinearLayoutManager(this@ShowMovieActivity)
            setHasFixedSize(true)
        }

        binding?.edtSearchNameMovie?.doAfterTextChanged {
            it?.let{
                itemMovieAdapter.submitList(listFilter.filter { movie ->
                    movie.nameCatelory.contains(it.toString())
                }.toMutableList())
                itemMovieAdapter.notifyDataSetChanged()
            }
        }

        addMovieViewModel.getAllMovie()
        addMovieViewModel.dataMovie.observe(this, Observer {
            it?.let {
                Log.d("catelory_list", it.toString())
                listShowMovie.clear()
                listShowMovie.addAll(it)
                itemMovieAdapter.submitList(listShowMovie)
                itemMovieAdapter.notifyDataSetChanged()
            }
        })

        addMovieViewModel.showListSpinner().observe(this, Observer {
            binding?.spinnerShowMovie?.run {
                it?.let {
                    listSpinner.add("All Catelory Movie")
                    it.forEach { string ->
                        listSpinner.add(string)
                    }
                    adapter =
                        ArrayAdapter(context, android.R.layout.simple_list_item_1, listSpinner)
                    setSelection(0)
                }
            }
        })

        addMovieViewModel.isSelectSpiner.observe(this, Observer {
            it?.let { str ->
                val text = "All Catelory Movie"
                listFilter = listShowMovie.filter{
                    if(str.equals(text)){
                        true
                    }else{
                        it.nameCatelory == str
                    }
                }.toMutableList()
                itemMovieAdapter.submitList(listFilter)
                itemMovieAdapter.notifyDataSetChanged()
            }
        })
    }
}