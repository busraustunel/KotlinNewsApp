package com.example.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.database.entity.Category
import com.example.newsapp.databinding.CategoryListItemBinding

class CategoriesAdapter(val context: Context, val categories: List<Category>, val onClick:(category:Category) -> Unit): RecyclerView.Adapter<CategoriesAdapter.CategoriesListViewHolder>(), CategoriesClickListener {
    class CategoriesListViewHolder(var binding: CategoryListItemBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListViewHolder {
        val binding = CategoryListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CategoriesListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesListViewHolder, position: Int) {

        holder.binding.category = categories[position]
        holder.binding.listener = this


    }

    override fun onCategoriesClicked(category: Category) {
        val position = categories.indexOf(category)
        onClick(categories[position])
    }
}