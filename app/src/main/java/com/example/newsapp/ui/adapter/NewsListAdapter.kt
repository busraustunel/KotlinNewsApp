package com.example.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.data.api.model.News
import com.example.newsapp.databinding.NewsListItemBinding

class NewsListAdapter(val context:Context, val newsList:List<News>): RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {
    class NewsListViewHolder(binding: NewsListItemBinding):RecyclerView.ViewHolder(binding.root) {

        val ivNews = binding.ivNews
        val tvNews = binding.tvNews

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return NewsListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val news = newsList[position]

        holder.ivNews.load(news.imageUrl)
        holder.tvNews.text = news.title
    }
}