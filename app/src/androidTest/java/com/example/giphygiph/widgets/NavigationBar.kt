package com.example.giphygiph.widgets

import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.giphygiph.R
import com.example.giphygiph.helpers.ClickUtils
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class NavigationBar {
    var tag: String = NavigationBar::class.java.simpleName

    private val clickUtils: ClickUtils = ClickUtils()

    @Throws(Exception::class)
    fun waitFor() {
        val trendingChooseLocationFragment: Matcher<View> = Matchers.allOf(ViewMatchers.withId(R.id.nav_view))

        try {
            Espresso.onView(trendingChooseLocationFragment)
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } catch (e: Exception) {
            Log.e(tag, "Exception while performing sleep action: ", e);
        }
    }

    fun clickTrending() {
        val campaignsButton: Matcher<View> = Matchers.allOf(ViewMatchers.withId(R.id.navigation_trending))
        clickUtils.clickUiObjectAfterWait(campaignsButton)
    }

    fun clickSearch() {
        val myActivityButton: Matcher<View> = Matchers.allOf(ViewMatchers.withId(R.id.navigation_search))
        clickUtils.clickUiObjectAfterWait(myActivityButton)
    }

    fun clickNotification() {
        val myAccountButton: Matcher<View> = Matchers.allOf(ViewMatchers.withId(R.id.navigation_notifications))
        clickUtils.clickUiObjectAfterWait(myAccountButton)
    }
}