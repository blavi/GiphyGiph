package com.example.giphygiph.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.giphygiph.intent.UserIntent
import com.example.giphygiph.interactor.FetchGifsInteractor
import com.example.giphygiph.viewstate.TrendingViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TrendingViewModel @ViewModelInject constructor(private val interactor: FetchGifsInteractor) : ViewModel() {

    val userIntent = Channel<UserIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<TrendingViewState>(TrendingViewState.Idle)
    val state: StateFlow<TrendingViewState>
        get() = _state

    init {
        consumeIntent()
    }

    private fun consumeIntent() {
        viewModelScope.launch {
            userIntent.receiveAsFlow().collect {
                when (it) {
                    is UserIntent.LoadGifs -> loadGifs()
                    is UserIntent.ShareGif -> shareGif(it.gif)
                }
            }
        }
    }

    fun loadGifs() {
        viewModelScope.launch {
            _state.value = TrendingViewState.Loading

            _state.value = interactor.fetchGifs()
        }
    }

    private fun shareGif(gif: GifDrawable) {
        viewModelScope.launch {
            _state.value = TrendingViewState.SharingGif(gif)
        }
    }

}