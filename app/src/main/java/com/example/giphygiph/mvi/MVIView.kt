package com.example.giphygiph.mvi

import com.example.giphygiph.ui.base.BaseFragment


abstract class MVIView <S : State, I : Intent> : BaseFragment(), StateConsumer<S>, IntentProducer<I> by ChannelIntentProducer()