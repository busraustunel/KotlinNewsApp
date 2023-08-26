package com.example.newsapp.data.state

import com.example.newsapp.data.api.model.News

sealed class NewsState {
    object Idle:NewsState()
    object Loading:NewsState()

    class Success(val news: List<News>):NewsState()
    class Error(val throwable: Throwable? = null): NewsState()
}