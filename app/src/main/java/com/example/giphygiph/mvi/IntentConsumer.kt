package com.example.giphygiph.mvi

import kotlinx.coroutines.flow.Flow

/**
 * MVI IntentConsumer should be able to accept Intents from the View. In case of applying in MVVM,
 * the ViewModel is the one that should consume the Intents.
 */
interface IntentConsumer<I : Intent, C : Change> {
    val consumer: FlowInteractor<I, C>
}

//typealias FlowInteractor<I, C> = suspend FlowCollector<C>.(intent: I) -> Unit
typealias FlowInteractor<I, C> = suspend (intent: I) -> Flow<C>