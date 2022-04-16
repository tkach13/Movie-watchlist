package com.benten.moviewatchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benten.moviewatchlist.databinding.LayoutMovieItemBinding
import com.bumptech.glide.Glide

class MoviesAdapter(val movieList: MutableList<Movie>) : RecyclerView.Adapter<MoviesViewHolder>() {
    private lateinit var itemClickListener: (Movie,Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            LayoutMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    fun setOnItemCLickListener(clickListener: (Movie, Int) -> Unit) {
        itemClickListener = clickListener
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movieList[position]
        Glide.with(holder.itemView.context).load(movie.picture).into(holder.binding.ivImagePoster)
        holder.binding.tvMovieName.text = movie.movieName
        holder.binding.tvMovieName.setOnClickListener {
            itemClickListener.invoke(movie,position)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

data class Movie(
    var movieName: String,
    val picture: String,
    val isChecked: Boolean = false
)

class MoviesViewHolder(val binding: LayoutMovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}