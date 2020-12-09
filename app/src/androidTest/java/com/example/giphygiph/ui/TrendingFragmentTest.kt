package com.example.giphygiph.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.giphygiph.R
import com.example.giphygiph.adapter.GifsAdapter
import com.example.giphygiph.util.EspressoIdlingResourceRule
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TrendingFragmentTest {
//    @get:Rule
//    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    val LIST_ITEM_IN_TEST = 0

    @Test
    fun test_GifsListVisible_onAppLaunch() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.progressBar)).check(matches(CoreMatchers.not(isDisplayed())))
    }

    @Test
    fun test_GifSelected_AppSelectorDisplayed() {
        val expectedIntent: Matcher<Intent> = allOf(
            hasAction(Intent.ACTION_SEND),
            hasType("image/gif")
        )

        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<GifsAdapter.DataViewHolder>(
                    LIST_ITEM_IN_TEST,
                    ViewActions.longClick()
                )
            )

        intended(expectedIntent)

//        assertThat(intent).hasAction(Intent.ACTION_VIEW)
//        assertThat(intent).categories().containsExactly(Intent.CATEGORY_BROWSABLE)
//        assertThat(intent).hasData(Uri.parse("www.google.com"))
//        assertThat(intent).extras().containsKey("key1")
//        assertThat(intent).extras().string("key1").isEqualTo("value1")
//        assertThat(intent).extras().containsKey("key2")
//        assertThat(intent).extras().string("key2").isEqualTo("value2")
    }
}