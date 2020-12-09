package com.example.giphygiph.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.example.giphygiph.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenAppTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    var mDevice: UiDevice? = null
    var PEOPLE_APP = "People"
    var MY_APP = "XING"
    var userContactName = "Android Tester"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    @Throws(Exception::class)
    fun testOpenApp() {

//        // Espresso: login the user, navigate to contact sync
//        // and enable clicking on toggle
//        logInUser()
//        onView(withText(R.string.sync_button)).perform(click())
//        onView(withId(R.id.contacts_sync_enable_toggle)).perform(click())

        // UIAutomator: navigate to People app
        mDevice?.let {
            it.pressHome()
            val gifsApp = it.findObject(UiSelector().text("GiphyGiph"))
            gifsApp.clickAndWaitForNewWindow()

            onView(withId(com.example.giphygiph.R.id.recyclerView)).check(
                ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

//            // UIAutomator: check that contact is present after sync was triggered
//            val contactName = mDevice!!.findObject(UiSelector().text(userContactName))
//            assertTrue(contactName.exists())
//
//            // UIAutomator: navigate back to my app under testm
//            Device.pressHome()
//            val myApp = mDevice!!.findObject(UiSelector().text(MY_APP))
//            myApp.clickAndWaitForNewWindow()
//
//            // Espresso: turn off contact sync and logout
//            onView(withId(R.id.contacts_sync_enable_toggle)).perform(click())
//            onView(withId(R.id.logout)).perform(click())
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }
}