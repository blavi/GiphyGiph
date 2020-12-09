package com.example.giphygiph.tests

import com.example.giphygiph.bases.EspressoTestBase
import com.example.giphygiph.constants.Timeouts
import com.example.giphygiph.screens.SearchScreen
import com.example.giphygiph.screens.TrendingScreen
import com.example.giphygiph.widgets.NavigationBar
import org.junit.Test

class MainScreenTests: EspressoTestBase() {
    private val navigationBar: NavigationBar = NavigationBar()
    private val trendingScreen: TrendingScreen = TrendingScreen()
    private val searchScreen: SearchScreen = SearchScreen()
//    private val myActivity: MyActivity = MyActivity()

    @Test(timeout = Timeouts.TEST_TIMEOUT_SHORT.toLong())
    fun gifsDiplayed() {
//        trendingScreen.waitFor()

        trendingScreen.checkGifsAreLoaded()
    }

    @Test(timeout = Timeouts.TEST_TIMEOUT_SHORT.toLong())
    fun userNavigatesToSearchSection() {
        // click 'My Activity' button and verify that it works
        navigationBar.waitFor()
        navigationBar.clickSearch()
//        searchScreen.isMyActivityTitleVisible()
        searchScreen.areSearchComponentsVisible()
    }
}
