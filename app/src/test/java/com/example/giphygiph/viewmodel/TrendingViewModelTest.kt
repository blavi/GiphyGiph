package com.example.giphygiph.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.giphygiph.interactor.FetchGifsInteractor
import com.example.giphygiph.model.Data
import com.example.giphygiph.model.Gifs
import com.example.giphygiph.model.Meta
import com.example.giphygiph.model.Pagination
import com.example.giphygiph.utils.MainCoroutineRule
import com.example.giphygiph.utils.getOrAwaitValueTest
import com.example.giphygiph.viewstate.TrendingViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TrendingViewModelTest {
    private lateinit var viewModel: TrendingViewModel
    private lateinit var gifsService: FetchGifsInteractor

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        gifsService = mockk<FetchGifsInteractor>()
        viewModel = TrendingViewModel(gifsService)
    }

    @Test
    fun test_LoadGifs_Successful() = mainCoroutineRule.runBlockingTest  {
        val data = ArrayList<Data>()
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())
        data.add(mockk<Data>())

        val gifs = Gifs(
            data,
            mockk<Pagination>(),
            mockk<Meta>()
        )

        coEvery { gifsService.fetchGifs() } returns TrendingViewState.LoadedGifs(gifs)

        viewModel.loadGifs()

        coVerify { gifsService.fetchGifs() }

        Assert.assertEquals(10, (viewModel.state.asLiveData().getOrAwaitValueTest() as TrendingViewState.LoadedGifs).gifs.data.size)

        Assert.assertEquals(TrendingViewState.LoadedGifs(gifs), viewModel.state.asLiveData().getOrAwaitValueTest())
    }

    @Test
    fun test_LoadGifs_Error() = mainCoroutineRule.runBlockingTest  {
        coEvery { gifsService.fetchGifs() } returns TrendingViewState.Error("")

        viewModel.loadGifs()

        coVerify { gifsService.fetchGifs() }

        Assert.assertEquals(TrendingViewState.Error(""), viewModel.state.asLiveData().getOrAwaitValueTest())
    }
}