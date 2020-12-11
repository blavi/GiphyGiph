package com.example.giphygiph.api

import com.example.giphygiph.model.Gifs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {

    @GET("trending")
    suspend fun getTrendingGifs(@Query("api_key") apiKey: String, @Query("limit") limit: Int): Response<Gifs>

    @GET("search")
    suspend fun searchGifs(@Query("api_key") apiKey: String,  @Query("q") searchQuery: String, @Query("limit") limit: Int): Response<Gifs>
}