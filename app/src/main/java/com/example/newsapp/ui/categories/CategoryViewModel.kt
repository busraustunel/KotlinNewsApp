package com.example.newsapp.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.repo.CategoryRepository
import com.example.newsapp.data.state.CategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository): ViewModel() {

    private val _categoryState:MutableStateFlow<CategoryState> = MutableStateFlow(CategoryState.Idle)
    val categoryState:StateFlow<CategoryState> = _categoryState


    fun getCategories() {
        viewModelScope.launch {
            _categoryState.value = CategoryState.Loading
            val categoryList = categoryRepository.getCategories()
            if(categoryList.isEmpty()) {
                _categoryState.value = CategoryState.Empty
            } else {
                _categoryState.value = CategoryState.Success(categoryList)
            }
            }


        }
}