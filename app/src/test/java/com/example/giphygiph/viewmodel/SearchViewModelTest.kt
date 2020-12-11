package com.example.giphygiph.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.giphygiph.interactor.FetchGifsInteractor
import com.example.giphygiph.interactor.SearchGifsInteractor
import com.example.giphygiph.model.Data
import com.example.giphygiph.model.Gifs
import com.example.giphygiph.model.Meta
import com.example.giphygiph.model.Pagination
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
    private lateinit var gifsService: SearchGifsInteractor

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        gifsService = mockk<SearchGifsInteractor>()
        viewModel = SearchViewModel(gifsService)
    }

    @Test
    fun test_SearchGifs_Successful() = mainCoroutineRule.runBlockingTest  {
        val data = ArrayList<Data>()
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())

        val gifs = Gifs(
            data,
            mockk<Pagination>(),
            mockk<Meta>()
        )

        coEvery { gifsService.searchGifs("") } returns SearchViewState.LoadedGifs(gifs)

        viewModel.searchGifs("")

        coVerify { gifsService.searchGifs("") }

        assertEquals(3, (viewModel.state.asLiveData().getOrAwaitValueTest() as SearchViewState.LoadedGifs).gifs.data.size)

        assertEquals(SearchViewState.LoadedGifs(gifs), viewModel.state.asLiveData().getOrAwaitValueTest())
    }

    @Test
    fun test_SearchGifs_Error() = mainCoroutineRule.runBlockingTest  {
        coEvery { gifsService.searchGifs("") } returns SearchViewState.Error("")

        viewModel.searchGifs("")

        coVerify { gifsService.searchGifs("") }

        assertEquals(SearchViewState.Error(""), viewModel.state.asLiveData().getOrAwaitValueTest())
    }
}