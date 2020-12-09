package com.example.giphygiph.viewstate

import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.giphygiph.model.Gifs

sealed class TrendingViewState {
    object Idle: TrendingViewState()
    object Loading: TrendingViewState()
    data class LoadedGifs(val gifs: Gifs): TrendingViewState()
    data class SharingGif(val gif: GifDrawable): TrendingViewState()
    data class Error(val error: String?): TrendingViewState()
}