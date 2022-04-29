package com.benten.moviewatchlist.models.responseModels

import com.benten.moviewatchlist.models.MovieItem
import com.google.gson.annotations.SerializedName

data class PopularsResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val results:List<MovieItem>
)
