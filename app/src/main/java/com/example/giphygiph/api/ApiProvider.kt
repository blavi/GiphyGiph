package com.example.giphygiph.api

import com.example.giphygiph.intent.UserIntent
import com.example.giphygiph.model.Gifs
import retrofit2.Response

interface ApiProvider {
    suspend fun getTrendingGifs(): Gifs

    suspend fun searchGifs(query: String): Gifs
}