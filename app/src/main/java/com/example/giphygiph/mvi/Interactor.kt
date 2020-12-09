package com.example.giphygiph.mvi

import kotlinx.coroutines.flow.Flow

/**
 * An Interactor's purpose is to create a flow of changes that are effects of the given Intent
 */
interface Interactor<I : Intent, C : Change> {
    operator fun invoke(intent: I): Flow<C>
}