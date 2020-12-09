package com.example.giphygiph.tests

import com.example.giphygiph.ui.SearchFragmentTest
import com.example.giphygiph.ui.TrendingFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    TrendingFragmentTest::class,
    SearchFragmentTest::class
)
class TestsSuite