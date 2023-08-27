package com.example.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.database.entity.Category
import com.example.newsapp.databinding.CategoryListItemBinding

class CategoriesAdapter(val context: Context, val categories: List<Category>, val onClick:(category:Category) -> Unit): RecyclerView.Adapter<CategoriesAdapter.CategoriesListViewHolder>() {
    class CategoriesListViewHolder(binding: CategoryListItemBinding): RecyclerView.ViewHolder(binding.root) {

        val tvCategoryName = binding.tvCategoryName

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListViewHolder {
        val binding = CategoryListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoriesListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesListViewHolder, position: Int) {

        holder.tvCategoryName.text = categories[position].name
        holder.itemView.setOnClickListener {
            onClick(categories[position])
        }
    }
}