package com.example.giphygiph.mvi

import androidx.lifecycle.LiveData

/**
 * MVI StateProducer needs to provide the state stream that can be observed by the View. In case
 * of MVVM, the ViewModel should implement this.
 */
interface StateProducer<I : Intent, S : State> {

    fun getState(producer: IntentProducer<I>): LiveData<S>
}