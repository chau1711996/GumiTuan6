package com.example.roomdatamvvm.activities.catelory

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatamvvm.R
import com.example.roomdatamvvm.activities.movie.AddMovieActivity
import com.example.roomdatamvvm.database.reposity.CateloryReposity
import com.example.roomdatamvvm.databinding.ActivityCateloryBinding
import com.example.roomdatamvvm.model.CateloryMovie
import com.example.roomdatamvvm.utils.Clicks

class CateloryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCateloryBinding
    private val cateloryViewModel: CateloryViewModel by lazy {
        ViewModelProvider(
            this,
            CateloryViewModel.CateloryViewModelFactory(this.application)
        )[CateloryViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_catelory)

        binding.cateloryViewModel = cateloryViewModel
        binding.lifecycleOwner = this
        binding.openAddMovieButton.setOnClickListener(this)
        displayCateloryList()
    }

    private fun displayCateloryList() {
        cateloryViewModel.getAllCatelory().observe(this, Observer {
            Log.d("catelory_list", it.toString())
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.open_add_movie_button ->{
                val intent = Intent(this, AddMovieActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


}