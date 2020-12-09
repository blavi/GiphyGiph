package com.example.giphygiph.screens

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.giphygiph.R
import com.example.giphygiph.helpers.ClickUtils
import com.example.giphygiph.helpers.UiUtils
import com.example.giphygiph.helpers.VisibilityUtils
import junit.framework.Assert.assertTrue
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class TrendingScreen {
    var tag: String = TrendingScreen::class.java.simpleName
    private val clickUtils: ClickUtils = ClickUtils()
    private val visibilityUtils: VisibilityUtils = VisibilityUtils()
    private val uiUtils: UiUtils = UiUtils()

//    fun checkMyAccountTitle() {
//        val textView = Matchers.allOf(
//            withText(R.string.title_trending),
//            uiUtils.childAtPosition(
//                Matchers.allOf(
//                    withId(R.id.main_toolbar),
//                    uiUtils.childAtPosition(withId(R.id.container), 0)
//                ), 0
//            ),
//            ViewMatchers.isDisplayed()
//        )
//        Assert.assertTrue(
//            "My account title not visible",
//            visibilityUtils.checkIfUIObjectIsVisible(textView, 5)
//        )
//    }

    @Throws(Exception::class)
    fun waitFor() {
        val trendingChooseLocationFragment: Matcher<View> = Matchers.allOf(withText(R.string.title_trending), Matchers.instanceOf(TextView::class.java))
        try {
            Espresso.onView(trendingChooseLocationFragment).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } catch (e: Exception) {
            Log.e(tag, "Exception while performing sleep action: ", e)
        }
    }

    fun checkGifsAreLoaded() {
        val yourRecycler = onView(withId(R.id.recyclerView))

        yourRecycler.check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assertTrue(view is RecyclerView &&
                    view.adapter != null && view.adapter?.itemCount?:-1 > 0
            )

        }
    }
}