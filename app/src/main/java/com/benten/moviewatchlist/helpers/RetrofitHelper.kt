package com.benten.moviewatchlist.helpers

import com.benten.moviewatchlist.api.MoviesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
     val popularsApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoviesApi::class.java)
}