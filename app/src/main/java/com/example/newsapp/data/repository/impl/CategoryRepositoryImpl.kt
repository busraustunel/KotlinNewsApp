package com.example.newsapp.data.repository.impl

import com.example.newsapp.data.database.dao.CategoryDao
import com.example.newsapp.data.database.entity.Category
import com.example.newsapp.data.repository.repo.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao): CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        return categoryDao.getCategories()
    }
}