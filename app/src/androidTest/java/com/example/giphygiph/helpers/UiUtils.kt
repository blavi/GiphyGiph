package com.example.giphygiph.helpers

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert

class UiUtils {

    private val visibilityUtils: VisibilityUtils = VisibilityUtils()

    fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

//    fun isErrorBannerDisplayed() {
//        val errorBanner: Matcher<View> = CoreMatchers.allOf(ViewMatchers.withId(R.id.error_banner))
//        Assert.assertTrue("Login title not visible", visibilityUtils.checkIfUIObjectIsVisible(errorBanner, 5))
//    }
}