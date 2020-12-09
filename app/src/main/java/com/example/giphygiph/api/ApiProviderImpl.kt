package com.example.giphygiph.api

import com.example.giphygiph.BuildConfig
import javax.inject.Inject

class ApiProviderImpl @Inject constructor(private val api: ApiEndpoints): ApiProvider{

    override suspend fun getTrendingGifs() = api.getTrendingGifs(BuildConfig.API_KEY, 10)

    override suspend fun searchGifs(query: String) = api.searchGifs(BuildConfig.API_KEY, query, 10)
}