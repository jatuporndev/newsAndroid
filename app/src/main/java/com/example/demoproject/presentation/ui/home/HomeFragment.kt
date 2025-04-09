package com.example.demoproject.presentation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.demoproject.databinding.FragmentHomeBinding
import com.example.demoproject.presentation.ui.home.adapter.NewsAdapter
import com.example.demoproject.presentation.ui.home.adapter.NewsType2Adapter
import com.example.demoproject.utility.HorizontalSpaceItemDecoration
import com.example.demoproject.utility.Util.dpToPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var news1Adapter: NewsAdapter
    private lateinit var news2Adapter: NewsAdapter
    private lateinit var newsType2Adapter: NewsType2Adapter
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spacing = 8.dpToPx(requireContext())

        news1Adapter = NewsAdapter()
        binding.firstRecyclerView.adapter = news1Adapter
        binding.firstRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(spacing))

        news2Adapter = NewsAdapter()
        binding.secondRecyclerView.adapter = news2Adapter
        binding.secondRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(spacing))

        newsType2Adapter = NewsType2Adapter()
        binding.recycleViewNews.adapter = newsType2Adapter

        observeData()
        viewModel.fetchHeadlineTechnology()
        viewModel.fetchHeadlineSport()
        viewModel.fetchNews()

    }

    private fun observeData() {

        lifecycleScope.launch {
            viewModel.stateHeadline.collect {
                when(it) {
                    is HeadlineState.Loading -> {

                    }

                    is HeadlineState.Success -> {
                        val data = it.data.articles
                        when(it.section) {
                             1 -> news1Adapter.submitList(data)
                             2 -> news2Adapter.submitList(data)
                        }

                    }

                    is HeadlineState.Error -> {

                    }

                }
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect {
                when(it) {
                    is HomeState.Loading -> {

                    }
                    is HomeState.Success -> {
                        newsType2Adapter.submitList(it.data.articles)
                    }
                    is HomeState.Error -> {

                    }
                }
            }
        }
    }
}