package com.example.newsapp.data.database.repository

import com.example.newsapp.data.database.dao.UserDao
import com.example.newsapp.data.database.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao:UserDao):UserRepository {

    override suspend fun insert(user:User):Long {
        return userDao.insert(user)
    }
}