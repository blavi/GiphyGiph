package com.example.giphygiph.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.giphygiph.R
import com.example.giphygiph.util.EspressoIdlingResourceRule
import com.example.giphygiph.util.RecyclerViewMatchers.hasItemCount
import com.example.giphygiph.util.launchFragmentInHiltContainer
import org.hamcrest.Matchers.not
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class SearchFragmentTest {

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun test_ASearchContainer_IsDisplayed() {
        launchFragmentInHiltContainer<SearchFragment> { }

        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))

        onView(withId(R.id.searchBtn)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun test_GifsList_IsDisplayed_WithValidInput() {
        launchFragmentInHiltContainer<SearchFragment> { }

        onView(withId(R.id.searchEditText)).perform(typeText("snow"))

        onView(withId(R.id.searchBtn)).perform(click())

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).check(matches((hasItemCount(10))))
    }

    @Test
    fun test_GifsList_NotDisplayed_WithEmptyInput() {
        launchFragmentInHiltContainer<SearchFragment> { }

        onView(withId(R.id.searchBtn)).perform(click())

        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun test_GifsList_IsDisplayed_WithInvalidInput() {
        launchFragmentInHiltContainer<SearchFragment> { }

        onView(withId(R.id.searchEditText)).perform(typeText("#"))

        onView(withId(R.id.searchBtn)).perform(click())

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))

        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
    }
}