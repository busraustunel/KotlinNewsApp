package com.example.newsapp.data.repository.repo

import com.example.newsapp.data.api.model.News

interface NewsRepository {

    suspend fun getNewsDetail(category: String):List<News>


}