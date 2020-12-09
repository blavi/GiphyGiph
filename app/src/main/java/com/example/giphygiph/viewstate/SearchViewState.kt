package com.example.giphygiph.viewstate

import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.giphygiph.model.Gifs

sealed class SearchViewState {
    object Idle: SearchViewState()
    object Loading: SearchViewState()
    object NoInternetConnection: SearchViewState()
    data class LoadedGifs(val gifs: Gifs) : SearchViewState()
    data class SharingGif(val gif: GifDrawable) : SearchViewState()
    data class Error(val error: String?) : SearchViewState()
}