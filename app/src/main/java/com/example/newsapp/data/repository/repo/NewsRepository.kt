package com.example.newsapp.data.repository.repo

import com.example.newsapp.data.api.model.News
import com.example.newsapp.data.api.model.Response

interface NewsRepository {

    suspend fun getNewsDetail():List<News>
}