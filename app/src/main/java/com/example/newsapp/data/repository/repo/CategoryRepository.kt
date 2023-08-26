package com.example.newsapp.data.repository.repo

import com.example.newsapp.data.database.entity.Category

interface CategoryRepository {

    suspend fun getCategories():List<Category>
}