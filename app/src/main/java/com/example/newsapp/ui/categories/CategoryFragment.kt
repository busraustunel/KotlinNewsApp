package com.example.newsapp.ui.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.state.CategoryState
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.ui.adapter.CategoriesAdapter
import kotlinx.coroutines.launch


class CategoryFragment : Fragment(R.layout.fragment_category) {

    lateinit var binding: FragmentCategoryBinding
    private val viewModel:CategoryViewModel by activityViewModels()
    lateinit var adapter: CategoriesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCategoryBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCategories()
        observeCategoriesState()
    }

    private fun observeCategoriesState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.categoryState.collect {
                    when(it) {
                        is CategoryState.Success -> {
                            adapter = CategoriesAdapter(requireContext(),it.categories) {
                                val action = CategoryFragmentDirections.actionCategoryFragmentToNewsFragment(it.value)
                                findNavController().navigate(action)
                            }
                            binding.rvCategories.adapter = adapter
                        }
                        CategoryState.Error -> {

                        }
                        CategoryState.Empty -> {

                        }
                        CategoryState.Loading -> {

                        }
                        CategoryState.Idle -> {

                        }
                    }
                }
            }
        }
    }

}