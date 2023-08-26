package com.example.newsapp.data.state

import com.example.newsapp.data.database.entity.Category

sealed class CategoryState {
    object Idle:CategoryState()
    object Loading:CategoryState()
    object Empty:CategoryState()
    object Error:CategoryState()
    data class Success(val categories: List<Category>):CategoryState()
}