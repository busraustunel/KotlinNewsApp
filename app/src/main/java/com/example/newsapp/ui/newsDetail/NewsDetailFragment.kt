package com.example.newsapp.ui.newsDetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.data.state.NewsState
import com.example.newsapp.databinding.FragmentNewsDetailBinding
import com.example.newsapp.ui.adapter.NewsListAdapter
import kotlinx.coroutines.launch


class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    lateinit var binding: FragmentNewsDetailBinding
    private val viewModel: NewsDetailViewModel by activityViewModels()
    lateinit var adapter: NewsListAdapter
    val args: NewsDetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNewsDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        viewModel.getNews(args.category)
        observeNewsState()


    }

    private fun observeNewsState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.newsState.collect {
                    when(it) {
                        is NewsState.Idle -> {}
                        is NewsState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.rvHome.isVisible = false
                        }
                        is NewsState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.rvHome.isVisible = true
                            adapter = NewsListAdapter(requireContext(), it.news)
                            binding.rvHome.adapter = adapter

                        }
                        is NewsState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.rvHome.isVisible = false
                            AlertDialog.Builder(requireContext()).setMessage("Error").create().show()
                        }
                    }
                }
            }
        }
    }

}