package com.example.giphygiph

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GifyGifApp : Application()  {
    override fun onCreate() {
        super.onCreate()

        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()
    }
}