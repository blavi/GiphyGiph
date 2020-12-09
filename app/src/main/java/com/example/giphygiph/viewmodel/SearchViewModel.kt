package com.example.giphygiph.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.giphygiph.intent.UserIntent
import com.example.giphygiph.repository.Repository
import com.example.giphygiph.viewstate.SearchViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(private val repository: Repository)  : ViewModel() {

    val userIntent = Channel<UserIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<SearchViewState>(SearchViewState.Idle)
    val state: StateFlow<SearchViewState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.receiveAsFlow().collect {
                when (it) {
                    is UserIntent.SearchGifs -> searchGifs(it.query)
                    is UserIntent.ShareGif -> shareGif(it.gif)
                }
            }
        }
    }

    fun searchGifs(query: String) {
        viewModelScope.launch {
            _state.value = SearchViewState.Loading
            _state.value = try {
                SearchViewState.LoadedGifs(repository.searchGifs(query))
            } catch (e: Exception) {
                SearchViewState.Error(e.localizedMessage)
            }
        }
    }

    private fun shareGif(gif: GifDrawable) {
        viewModelScope.launch {
            _state.value = SearchViewState.SharingGif(gif)
        }
    }
}