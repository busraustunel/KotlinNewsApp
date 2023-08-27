package com.example.newsapp.ui.categories

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsapp.Constants
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
        binding.toolbar.inflateMenu(R.menu.toolbar_menu)

        viewModel.getCategories()
        observeCategoriesState()
        listeners()
    }

    private fun listeners() {
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_logout -> {
                    logout()
                    true
                }
                else -> false
            }
        }
    }

    private fun logout() {
        val sharedPreferences = requireContext().getSharedPreferences(Constants.SHARED_PREF_NAME, AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(Constants.REMEMBER_ME_KEY, false).apply()
        findNavController().navigate(R.id.action_categoryFragment_to_loginFragment)
        sharedPreferences.edit().clear().apply()

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