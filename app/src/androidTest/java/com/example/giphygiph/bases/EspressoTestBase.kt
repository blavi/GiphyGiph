package com.example.giphygiph.bases

import android.util.Log
import androidx.test.rule.ActivityTestRule
import com.example.giphygiph.ui.MainActivity
import org.junit.AfterClass
import org.junit.Before
import org.junit.Rule

open class EspressoTestBase {

    private val app: AppLaunch = AppLaunch()

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        launchApp(activityRule)
    }

    companion object {
        @AfterClass
        fun cleanUp() {
            Log.i(EspressoTestBase::class.java.simpleName, "EspressoTestBase");
        }
    }

    @Throws(Exception::class)
    fun launchApp(ActivityRule: ActivityTestRule<*>?) {
        app.launchApp(ActivityRule)
    }
}