package com.example.giphygiph.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.giphygiph.model.Data
import com.example.giphygiph.model.Gifs
import com.example.giphygiph.model.Meta
import com.example.giphygiph.model.Pagination
import com.example.giphygiph.repository.Repository
import com.example.giphygiph.utils.MainCoroutineRule
import com.example.giphygiph.utils.getOrAwaitValueTest
import com.example.giphygiph.viewstate.SearchViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {
    private lateinit var viewModel: SearchViewModel
    private lateinit var gifsService: Repository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        gifsService = mockk<Repository>()
        viewModel = SearchViewModel(gifsService)
    }

    @Test
    fun test1() = mainCoroutineRule.runBlockingTest  {
        val data = ArrayList<Data>()
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())

        val gifs = Gifs(
            data,
            mockk<Pagination>(),
            mockk<Meta>()
        )

        coEvery { gifsService.searchGifs("") } returns gifs

        viewModel.searchGifs("")

        coVerify { gifsService.searchGifs("") }

        assertEquals(3, (viewModel.state.asLiveData().getOrAwaitValueTest() as SearchViewState.LoadedGifs).gifs.data.size)

        assertEquals(SearchViewState.LoadedGifs(gifs), viewModel.state.asLiveData().getOrAwaitValueTest())
    }
}