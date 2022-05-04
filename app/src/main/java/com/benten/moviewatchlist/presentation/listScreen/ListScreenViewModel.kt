package com.benten.moviewatchlist.presentation.listScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benten.moviewatchlist.models.MovieItem
import com.benten.moviewatchlist.presentation.listScreen.repos.MoviesRepo
import kotlinx.coroutines.launch

class ListScreenViewModel : ViewModel() {
    private val moviesMutableLiveData = MutableLiveData<List<MovieItem>>()
    private val moviesRepo = MoviesRepo.getInstance()

    init {
        viewModelScope.launch {
            moviesMutableLiveData.postValue(moviesRepo.getMovies("843c612d1207fdec63f0e6a5fd426d68").results)
        }
    }

    fun getMoviesLiveData(): LiveData<List<MovieItem>> {
        return moviesMutableLiveData
    }


}