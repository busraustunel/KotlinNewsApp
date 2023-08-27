package com.example.newsapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.data.api.model.News
import com.example.newsapp.databinding.NewsListItemBinding

class NewsListAdapter(val context:Context, val newsList:List<News>): RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {
    class NewsListViewHolder(binding: NewsListItemBinding):RecyclerView.ViewHolder(binding.root) {

        val ivNews = binding.ivNews
        val tvNewsAuthor = binding.tvNewsAuthor
        val tvContent = binding.tvNewsContent
        val tvNewsDate = binding.tvNewsDate
        val tvNewsTime = binding.tvNewsTime
        val tvNewsTitle = binding.tvNewsTitle
        val btnReadMore = binding.btnReadMore

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
        holder.tvNewsAuthor.text = news.author
        holder.tvContent.text = news.content
        holder.tvNewsDate.text = news.date
        holder.tvNewsTime.text = news.time
        holder.tvNewsTitle.text = news.title
        holder.btnReadMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(news.readMoreUrl)
            context.startActivity(intent)
        }



    }
}