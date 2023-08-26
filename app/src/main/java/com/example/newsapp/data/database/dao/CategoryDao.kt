package com.example.newsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.newsapp.data.database.entity.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Category")
    suspend fun getCategories():List<Category>
}