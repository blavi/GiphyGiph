package com.example.giphygiph.interactor

import com.example.giphygiph.repository.Repository
import com.example.giphygiph.viewstate.TrendingViewState
import javax.inject.Inject

class FetchGifsInteractor @Inject constructor(private val repository: Repository) {

    suspend fun fetchGifs(): TrendingViewState {
        val result = repository.getTrendingGifs()

        return when (result.code()) {
            200 -> TrendingViewState.LoadedGifs(result.body()!!)
            else -> TrendingViewState.Error(result.message())
        }
    }
}