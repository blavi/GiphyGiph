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
import com.example.giphygiph.databinding.FragmentDashboardBinding
import com.example.giphygiph.intent.UserIntent
import com.example.giphygiph.model.Gifs
import com.example.giphygiph.EspressoIdlingResource
import com.example.giphygiph.viewmodel.SearchViewModel
import com.example.giphygiph.viewstate.SearchViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var searchAdapter: GifsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupViewModel()
        setupUI()
        observeViewModel()
        setListeners()
    }

    private fun setListeners() {
        binding.searchBtn.setOnClickListener {
            if (binding.searchEditText.text.isNotEmpty()) {
                EspressoIdlingResource.increment()
                lifecycleScope.launch {
                    searchViewModel.userIntent.send(UserIntent.SearchGifs(binding.searchEditText.text.toString()))
                }
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            searchViewModel.state.collect {
                when (it) {
                    is SearchViewState.Idle -> {

                    }
                    is SearchViewState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is SearchViewState.LoadedGifs -> {
                        binding.progressBar.visibility = View.GONE
                        renderList(it.gifs)
                    }
                    is SearchViewState.SharingGif -> {
                        shareGif(it.gif)
                    }
                    is SearchViewState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setupUI() {
        searchAdapter = GifsAdapter({ giph : UserIntent.ShareGif -> initiateSharingGif(giph) })
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = searchAdapter
        }
    }

    private fun renderList(gifs: Gifs) {
        if (gifs.data.isNotEmpty()) {
            binding.recyclerView.visibility = View.VISIBLE
            searchAdapter.addData(gifs)
            searchAdapter.notifyDataSetChanged()
        }
        EspressoIdlingResource.decrement()
    }

    private fun initiateSharingGif(shareIntent: UserIntent): Boolean {
        lifecycleScope.launch {
            searchViewModel.userIntent.send(shareIntent)
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