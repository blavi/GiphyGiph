package com.example.giphygiph.bases

import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector

class AppLaunch {

    private val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
    private val device: UiDevice = UiDevice.getInstance(instrumentation)
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val tag = AppLaunch::class.java.simpleName

    @Throws(Exception::class)
    fun launchApp(activityRule: ActivityTestRule<*>?) {
        val intent = Intent(Intent.ACTION_MAIN)
        var retryCount = 4
        while (retryCount > 0) {
            retryCount--
            try {
                activityRule?.launchActivity(intent)
                break
            } catch (e: RuntimeException) {
                if (retryCount > 0) {
                    Log.i(tag, "Caught Runtime exception", e)
                    closeApp(activityRule)
                } else {
                    throw e
                }
            }
        }
    }

    @Throws(Exception::class)
    private fun closeApp(activityRule: ActivityTestRule<*>?) {
        try {
            activityRule?.finishActivity()
        } catch (e: IllegalStateException) {
            closeRecentApp()
        }
    }

    @Throws(Exception::class)
    fun closeRecentApp() {
        device.pressRecentApps()
        val app: UiObject = device.findObject(UiSelector().resourceId(context.packageName))
        app.swipeLeft(100)
        device.pressHome()
    }
}