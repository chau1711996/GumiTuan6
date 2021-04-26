package com.example.roomdatamvvm.activities.showMovie

import android.content.Context
import android.content.SharedPreferences
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
    private var listShowMovie = mutableListOf<Movie>()
    private var itemMovieAdapter = AdapterShowMovie()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_movie)
        binding?.lifecycleOwner = this
        binding?.addViewModel = addMovieViewModel

        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        val edit = sharedPreference.getString("EDIT", "")
        val spinner = sharedPreference.getInt("SPINNER", 0)

        Toast.makeText(this, "${edit} + pos: ${spinner}", Toast.LENGTH_SHORT).show()

        binding?.listShowMovie?.run {
            adapter = itemMovieAdapter
            layoutManager = LinearLayoutManager(this@ShowMovieActivity)
            setHasFixedSize(true)
        }




        addMovieViewModel.getAllMovie()
        addMovieViewModel.dataMovie.observe(this, Observer {
            it?.let {
                listShowMovie.clear()
                listShowMovie.addAll(it)
                itemMovieAdapter.submitList(listShowMovie)
                itemMovieAdapter.notifyDataSetChanged()
            }
        })

        binding?.edtSearchNameMovie?.setText(edit)

        addMovieViewModel.showListSpinner().observe(this, Observer {
            binding?.spinnerShowMovie?.run {
                it?.let {
                    listSpinner.add("All Catelory Movie")
                    it.forEach { string ->
                        listSpinner.add(string)
                    }
                    adapter =
                        ArrayAdapter(context, android.R.layout.simple_list_item_1, listSpinner)
                }
                setSelection(spinner)
            }
        })

        addMovieViewModel.isSelectSpiner.observe(this, Observer {
            it?.let { str ->
                val pos = binding?.spinnerShowMovie?.selectedItemPosition
                val name = binding?.edtSearchNameMovie?.text.toString()
                pos?.let {
                    editor.putInt("SPINNER", pos).apply()
                    showListFilter(name, str)
                }
            }
        })


        binding?.edtSearchNameMovie?.let {
            it.setText(edit)
            it.doAfterTextChanged {
                it?.let { str ->
                    val pos = binding?.spinnerShowMovie?.selectedItemPosition
                    editor.putString("EDIT", str.toString()).apply()
                    pos?.let {
                        showListFilter(str.toString(), listSpinner.get(pos))
                    }
                }
            }
        }
    }

    private fun showListFilter(name: String, catelory: String) {
        val listFilterCatelory = listShowMovie.filter {
            if (catelory.equals("All Catelory Movie")) {
                true
            } else {
                it.nameCatelory == catelory
            }
        }
        val listFilterName = listFilterCatelory.filter {
            if (name.equals("")){
                true
            }else{
                it.name.contains(name)
            }
        }
        itemMovieAdapter.submitList(listFilterName)
        itemMovieAdapter.notifyDataSetChanged()
    }
}