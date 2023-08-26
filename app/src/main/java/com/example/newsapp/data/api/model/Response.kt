package com.example.newsapp.data.api.model

data class Response<T>(
    val category: String,
    val `data`: List<News>,
    val success: Boolean
)