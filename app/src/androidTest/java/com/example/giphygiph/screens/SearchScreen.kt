package com.example.giphygiph.screens

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.giphygiph.R
import com.example.giphygiph.helpers.ClickUtils
import com.example.giphygiph.helpers.UiUtils
import com.example.giphygiph.helpers.VisibilityUtils
import org.junit.Assert

class SearchScreen {
    var tag: String = SearchScreen::class.java.simpleName
    private val clickUtils: ClickUtils = ClickUtils()
    private val visibilityUtils: VisibilityUtils = VisibilityUtils()
    private val uiUtils: UiUtils = UiUtils()

//    fun checkMyAccountTitle() {
//        val textView = Matchers.allOf(
//            withText(R.string.title_search),
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

    fun areSearchComponentsVisible() {
        val searchEditText = Espresso.onView(withId(R.id.searchEditText))
        val searchBtn = Espresso.onView(withId(R.id.searchBtn))

        Assert.assertTrue("Search Edit Text not visible", visibilityUtils.checkIfUIObjectIsVisible(searchEditText, 5))
        Assert.assertTrue("Search Button not visible", visibilityUtils.checkIfUIObjectIsVisible(searchBtn, 5))
    }
}