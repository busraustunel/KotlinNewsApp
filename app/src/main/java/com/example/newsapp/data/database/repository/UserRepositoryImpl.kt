package com.example.newsapp.data.database.repository

import com.example.newsapp.data.database.dao.UserDao
import com.example.newsapp.data.database.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao):UserRepository {
    override suspend fun insertUser(user: User) {
        return userDao.insert(user)
    }

    override suspend fun getUser(email: String, password: String): User? {
        return userDao.getUser(email, password)
    }

}