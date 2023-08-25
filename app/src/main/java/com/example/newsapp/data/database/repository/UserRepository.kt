package com.example.newsapp.data.database.repository

import com.example.newsapp.data.database.entity.User

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun getUser(email: String, password: String): User?
}