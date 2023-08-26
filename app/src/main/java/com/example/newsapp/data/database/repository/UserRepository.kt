package com.example.newsapp.data.database.repository

import com.example.newsapp.data.database.entity.User


interface UserRepository {

    suspend fun insert(user:User):Long
}