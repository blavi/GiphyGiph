package com.example.giphygiph.helpers

import android.app.Instrumentation
import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import org.hamcrest.Matcher

class ClickUtils {

    var tag: String = ClickUtils::class.java.simpleName

    private val waitUtil: WaitUtils = WaitUtils()
    private val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
    private val device: UiDevice = UiDevice.getInstance(instrumentation)

    @Throws(Exception::class)
    fun performActionOnUiObjectAfterWait(
        matcher: Matcher<View>,
        viewAction: ViewAction,
        waitTimeInSeconds: Int
    ) {
        try {
            Espresso.onView(matcher).perform(viewAction)
        } catch (e: Exception) {
            waitUtil.waitForXSeconds(waitTimeInSeconds)
            Espresso.onView(matcher).perform(viewAction)
        } finally {
            Log.e(tag, "Exception while performing sleep action: ")
        }
    }

    fun clickUiObjectAfterWait(matcher: Matcher<View>, waitTimeInSeconds: Int) {
        performActionOnUiObjectAfterWait(matcher, ViewActions.click(), waitTimeInSeconds)
    }

    fun clickUiObjectAfterWait(matcher: Matcher<View>) {
        clickUiObjectAfterWait(matcher, 10)
    }

    fun clickUiObjectAfterWait(uiObject: UiObject?) {
        uiObject?.click()
    }
}