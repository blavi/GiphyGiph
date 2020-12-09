package com.example.giphygiph.repository

import com.example.giphygiph.api.ApiProviderImpl
import javax.inject.Inject

class Repository @Inject constructor(private val apiHelperImpl: ApiProviderImpl) {
    suspend fun getTrendingGifs() = apiHelperImpl.getTrendingGifs()
    suspend fun searchGifs(query: String) = apiHelperImpl.searchGifs(query)
}