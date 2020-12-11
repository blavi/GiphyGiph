package com.example.giphygiph.interactor

import com.example.giphygiph.repository.Repository
import com.example.giphygiph.viewstate.SearchViewState
import javax.inject.Inject

class SearchGifsInteractor @Inject constructor(private val repository: Repository)  {
     suspend fun searchGifs(query: String): SearchViewState {
        val result = repository.searchGifs(query)

        return when (result.code()) {
            200 -> SearchViewState.LoadedGifs(result.body()!!)
            else -> SearchViewState.Error(result.message())
        }
    }
}