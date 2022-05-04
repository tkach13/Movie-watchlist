package com.benten.moviewatchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benten.moviewatchlist.databinding.LayoutMovieItemBinding
import com.benten.moviewatchlist.models.MovieItem
import com.bumptech.glide.Glide

class MoviesAdapter(val movieList: MutableList<MovieItem>) : RecyclerView.Adapter<MoviesViewHolder>() {
    private lateinit var itemClickListener: (MovieItem,Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            LayoutMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }
    fun updateList(movies:List<MovieItem>){
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun setOnItemCLickListener(clickListener: (MovieItem, Int) -> Unit) {
        itemClickListener = clickListener
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.tvMovieName.text = movie.originalTitle
        holder.binding.ivImagePoster.setImageURI("https://image.tmdb.org/t/p/w500${movie.posterPath}")
        holder.binding.tvRating.text = movie.voteAverage.toString()
        holder.binding.tvReleaseDate.text = movie.releaseDate
        holder.binding.root.setOnClickListener {
            itemClickListener.invoke(movie,position)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}



class MoviesViewHolder(val binding: LayoutMovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}