package com.benten.moviewatchlist.presentation.listScreen.repos

import com.benten.moviewatchlist.helpers.RetrofitHelper
import com.benten.moviewatchlist.models.responseModels.PopularsResponse

class MoviesRepo {

    suspend fun getMovies(apiKey: String): PopularsResponse {
        return RetrofitHelper.popularsApi.getPopularMovies(apiKey)
    }

    companion object {
        //this singleton
        private var instance: MoviesRepo? = null
        fun getInstance(): MoviesRepo {
            return if (instance != null) {
                instance!!
            } else {
                instance = MoviesRepo()
                instance!!
            }
        }

    }
}