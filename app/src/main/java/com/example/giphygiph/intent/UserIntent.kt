package com.example.giphygiph.intent

import com.bumptech.glide.load.resource.gif.GifDrawable

sealed class UserIntent {
    object LoadGifs : UserIntent()
    data class ShareGif(val gif: GifDrawable): UserIntent()
    data class SearchGifs(val query: String): UserIntent()
}