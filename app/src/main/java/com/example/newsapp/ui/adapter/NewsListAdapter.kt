package com.example.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.data.api.model.News
import com.example.newsapp.databinding.NewsListItemBinding

class NewsListAdapter(val context:Context, val newsList:List<News>, val onClick:(news:News) -> Unit): RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>(), ReadMoreClickListener {
    class NewsListViewHolder(var binding: NewsListItemBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding = DataBindingUtil.inflate<NewsListItemBinding>(LayoutInflater.from(parent.context), com.example.newsapp.R.layout.news_list_item, parent, false)
        return NewsListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {

        holder.binding.news = newsList[position]
        holder.binding.readMoreListener = this
    }
    companion object{
        @JvmStatic
        @BindingAdapter("android:loadImage")
        fun loadImage(view: ImageView, url:String?){
            view.load(url)
        }
    }

    override fun onReadMoreClicked(news: News) {
        onClick(news)
    }
}