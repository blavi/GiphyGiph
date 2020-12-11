package com.example.giphygiph.api

import com.example.giphygiph.model.Gifs
import retrofit2.Response

interface ApiProvider {
    suspend fun getTrendingGifs(): Response<Gifs>

    suspend fun searchGifs(query: String): Response<Gifs>
}