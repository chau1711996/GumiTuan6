package com.example.roomdatamvvm.activities.showMovie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatamvvm.R
import com.example.roomdatamvvm.databinding.ItemMovieBinding
import com.example.roomdatamvvm.model.Movie

class AdapterShowMovie: ListAdapter<Movie, AdapterShowMovie.ShowMovieHolder>(ShowMovieCallback()) {

    inner class ShowMovieHolder(private val viewItemMovie: ItemMovieBinding) : RecyclerView.ViewHolder(viewItemMovie.root){

        fun bind(movie: Movie){
            viewItemMovie.textCatelory.text = movie.nameCatelory
            viewItemMovie.textMovie.text = movie.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowMovieHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowMovieHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowMovieHolder, position: Int) {
        Log.d("catelory_list",getItem(position).name)

        holder.bind(getItem(position))
    }


    class ShowMovieCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
