package com.example.giphygiph.helpers

import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class WaitUtils {
    var tag: String = WaitUtils::class.java.simpleName

    fun waitForXSeconds(waitTimeInSeconds: Int) {

        val viewInteraction = Espresso.onView(ViewMatchers.isRoot())
        var waitTimeInMillis = (waitTimeInSeconds * 1000)
        var runWithSleep = false


        while (waitTimeInMillis > 0) {
            try {
                if (waitTimeInMillis >= 50000) {
                    viewInteraction.noActivity().perform(sleep(50000))
                    waitTimeInMillis -= 50000;
                } else {
                    viewInteraction.noActivity().perform(sleep(waitTimeInMillis))
                    waitTimeInMillis = 0;
                }
            } catch (e: Exception) {
                Log.e(tag, "Exception while performing sleep action: ", e)
                runWithSleep = true
            }

            if (runWithSleep) {
                try {
                    if (waitTimeInMillis >= 50000) {
                        Thread.sleep(50000)
                        waitTimeInMillis -= 50000
                    } else {
                        Thread.sleep(waitTimeInMillis.toLong())
                        waitTimeInMillis = 0
                    }
                } catch (e: InterruptedException) {
                    Log.e(tag, "Exception when sleeping: ", e)
                }
            }
        }
    }

    private fun sleep(milliSeconds: Int): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isRoot()
            }

            override fun getDescription(): String {
                return "Wait for at least $milliSeconds milliseconds"
            }

            override fun perform(uiController: UiController, view: View?) {
                try {
                    uiController.loopMainThreadForAtLeast(milliSeconds.toLong())
                } catch (e: Exception) {
                    Log.e(tag, "Exception thrown while waiting: ", e)
                }
            }
        }
    }
}