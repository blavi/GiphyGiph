package com.example.giphygiph.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private var logging   = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private var client : OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
    private const val baseURL : String = "https://api.giphy.com/v1/gifs/"

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}