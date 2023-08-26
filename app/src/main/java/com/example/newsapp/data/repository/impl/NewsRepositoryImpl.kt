package com.example.newsapp.data.repository.impl

import com.example.newsapp.data.api.model.News
import com.example.newsapp.data.api.model.Response
import com.example.newsapp.data.api.service.NewsService
import com.example.newsapp.data.repository.repo.NewsRepository
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val retrofit: Retrofit):NewsRepository {
    override suspend fun getNewsDetail(): List<News> {
        return retrofit.create(NewsService::class.java).getNewsDetail("science").data
    }

}