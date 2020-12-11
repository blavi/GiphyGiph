package com.example.giphygiph.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.giphygiph.adapter.GifsAdapter
import com.example.giphygiph.databinding.FragmentTrendingBinding
import com.example.giphygiph.intent.UserIntent
import com.example.giphygiph.model.Gifs
import com.example.giphygiph.EspressoIdlingResource
import com.example.giphygiph.viewmodel.TrendingViewModel
import com.example.giphygiph.viewstate.TrendingViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModels()
    private lateinit var binding: FragmentTrendingBinding
    private lateinit var gifsAdapter: GifsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTrendingBinding.inflate(layoutInflater)

        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
//        setupViewModel()
        observeViewModel()
        loadGiphs()
    }

    private fun loadGiphs() {
        EspressoIdlingResource.increment()
        lifecycleScope.launchWhenResumed {
            trendingViewModel.userIntent.send(UserIntent.LoadGifs)
        }
    }

    private fun setupUI() {
        gifsAdapter = GifsAdapter({ giph : UserIntent.ShareGif -> initiateSharingGif(giph) })
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = gifsAdapter
        }
    }

//    private fun setupViewModel() {
//        homeViewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(
//                ApiProviderImpl(
//                    RetrofitService.createService(ApiEndpoints::class.java)
//                )
//            )
//        ).get(HomeViewModel::class.java)
//    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            trendingViewModel.state.collect {
                when (it) {
                    is TrendingViewState.Idle -> {

                    }
                    is TrendingViewState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is TrendingViewState.LoadedGifs -> {
                        binding.progressBar.visibility = View.GONE
                        renderList(it.gifs)
                    }
                    is TrendingViewState.SharingGif -> {
                        shareGif(it.gif)
                    }
                    is TrendingViewState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(gifs: Gifs) {
        binding.recyclerView.visibility = View.VISIBLE
        gifsAdapter.addData(gifs)
        gifsAdapter.notifyDataSetChanged()
        EspressoIdlingResource.decrement()
    }

    private fun initiateSharingGif(shareIntent: UserIntent): Boolean {
        lifecycleScope.launch {
            trendingViewModel.userIntent.send(shareIntent)
        }
        return true
    }

    private fun shareGif(gif: GifDrawable) {
        val byteBuffer = gif.buffer
        val gifFile = File(requireContext().filesDir, "test.gif")

        val output = FileOutputStream(gifFile)
        val bytes = ByteArray(byteBuffer.capacity())
        (byteBuffer.duplicate().clear() as ByteBuffer).get(bytes)
        output.write(bytes, 0, bytes.size)
        output.close()

        val uri = FileProvider.getUriForFile(
                requireContext(),
        requireContext().applicationContext
                .packageName + ".provider", gifFile)

        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/gif"
        }
        startActivity(Intent.createChooser(shareIntent, "Send to"))
    }
}