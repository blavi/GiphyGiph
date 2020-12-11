package com.example.giphygiph.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphygiph.api.ApiProviderImpl
import com.example.giphygiph.repository.Repository

//class ViewModelFactory(private val apiHelperImpl: ApiProviderImpl) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(TrendingViewModel::class.java)) {
//            return TrendingViewModel(Repository(apiHelperImpl)) as T
//        }
//        throw IllegalArgumentException("Unknown class name")
//    }
//
//}