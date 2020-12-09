package com.example.giphygiph.mvi

/**
 * MVI StateConsumer needs to provide a way to consume (eg. display) the State. The View needs to
 * implement this.
 */
interface StateConsumer<T : State> {
    fun consume(state: T)
}