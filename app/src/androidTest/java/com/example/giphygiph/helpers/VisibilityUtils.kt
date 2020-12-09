package com.example.giphygiph.helpers

import android.app.Instrumentation
import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import org.hamcrest.Matcher
import org.hamcrest.core.StringContains

class VisibilityUtils {
    var tag: String = VisibilityUtils::class.java.simpleName

    private val waitUtil: WaitUtils = WaitUtils()
    private val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
    private val device: UiDevice = UiDevice.getInstance(instrumentation)

    fun checkIfUIObjectIsVisible(matcher: Matcher<View>, waitTimeInSeconds: Int): Boolean {
        var isVisible = false
        try {
            waitUtil.waitForXSeconds(waitTimeInSeconds)
            Espresso.onView(matcher).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            isVisible = true
        } catch (e: Exception) {
            Log.e(tag, "Exception while checking visibility!");
        }
        return isVisible
    }

    fun checkIfUIObjectIsVisible(objectToGet: UiObject?, waitTimeInSeconds: Int): Boolean {
        var isVisible = false
        try {
            waitUtil.waitForXSeconds(waitTimeInSeconds)
            if (objectToGet != null) {
                if(objectToGet.exists()){
                    isVisible = true
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "Exception while checking visibility!");
        }
        return isVisible
    }

    fun checkIfUIObjectIsVisible(viewInteraction: ViewInteraction, waitTimeInSeconds: Int): Boolean {
        var isVisible = false
        try {
            waitUtil.waitForXSeconds(waitTimeInSeconds)
            viewInteraction.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            isVisible = true
        } catch (e: Exception) {
            Log.e(tag, "Exception while checking visibility!");
        }
        return isVisible
    }

    fun getTextOfObject(textToParse: Int): String {
        return textToParse.toString()
    }

    fun checkTextOfUiObject(matcher: Matcher<View>, text: String): ViewInteraction? {
        return Espresso.onView(matcher).check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    fun checkPartialTextOfUiObject(matcher: Matcher<View>, text: String): ViewInteraction? {
        return Espresso.onView(matcher)
            .check(ViewAssertions.matches(ViewMatchers.withText(StringContains.containsString(text))))
    }

    fun checkTextOfUiObject(viewInteraction: ViewInteraction, text: String): ViewInteraction? {
        return viewInteraction.check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    fun getUiObjByText(text: String?): UiObject? {
        return device.findObject(UiSelector().text(text))
    }

    fun getUiObjByClass(className: String?): UiObject? {
        return device.findObject(UiSelector().className(className))
    }

    fun getUiObjByResourceId(id: String?): UiObject? {
        return device.findObject(UiSelector().resourceId(id))
    }

    fun getUiObjByClassNameAndText(className: String, text: String): UiObject? {
        return device.findObject(UiSelector().className(className).text(text))
    }

    fun getUiObjByResourceIdAndText(id: Int, text: String): UiObject? {
        return device.findObject(UiSelector().resourceId(id.toString()).text(text))
    }
}