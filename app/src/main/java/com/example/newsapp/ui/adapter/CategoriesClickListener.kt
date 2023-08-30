package com.example.newsapp.ui.adapter
import com.example.newsapp.data.database.entity.Category

interface CategoriesClickListener {
    fun onCategoriesClicked(category: Category)
}