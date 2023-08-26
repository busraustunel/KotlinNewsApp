package com.example.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.database.dao.CategoryDao
import com.example.newsapp.data.database.dao.UserDao
import com.example.newsapp.data.database.entity.Category
import com.example.newsapp.data.database.entity.User


@Database(entities = [User::class, Category::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao():UserDao

    abstract fun categoryDao():CategoryDao


}