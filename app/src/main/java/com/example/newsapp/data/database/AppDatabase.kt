package com.example.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.database.dao.UserDao
import com.example.newsapp.data.database.entity.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao():UserDao

}