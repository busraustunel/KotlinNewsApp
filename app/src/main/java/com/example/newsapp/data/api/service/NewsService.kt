package com.example.newsapp.data.api.service

import com.example.newsapp.data.api.model.News
import com.example.newsapp.data.api.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("news")
    suspend fun getNewsDetail(@Query("category") category: String):Response<List<News>>
}