package com.benten.moviewatchlist.api

import com.benten.moviewatchlist.models.responseModels.MovieDetailsResponseModel
import com.benten.moviewatchlist.models.responseModels.PopularsResponse
import retrofit2.http.*

interface MoviesApi {
    @Headers("platform: Web")
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String
    ): PopularsResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id")
        movieId: Int,
        @Query("api_key")
        apiKey: String
    ):MovieDetailsResponseModel
}