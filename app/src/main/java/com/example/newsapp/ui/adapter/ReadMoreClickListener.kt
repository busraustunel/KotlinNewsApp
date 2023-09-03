package com.example.newsapp.ui.adapter

import com.example.newsapp.data.api.model.News

interface ReadMoreClickListener {
    fun onReadMoreClicked(news: News)
}