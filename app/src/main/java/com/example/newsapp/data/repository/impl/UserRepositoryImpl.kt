package com.example.newsapp.data.repository.impl

import com.example.newsapp.data.database.dao.UserDao
import com.example.newsapp.data.database.entity.User
import com.example.newsapp.data.repository.repo.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao:UserDao): UserRepository {

    override suspend fun insert(user:User):Long {
        return userDao.insert(user)
    }

    override suspend fun getUser(email: String, password: String): User? {
        return userDao.getUser(email,password)
    }
}