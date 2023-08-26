package com.example.newsapp.data.state

import com.example.newsapp.data.database.entity.User

sealed class UserLoginState {
    object Idle:UserLoginState()
    object Checking:UserLoginState()

    object UserNotFound:UserLoginState()

    class Success(user: User?):UserLoginState()
    class Error(val throwable: Throwable? = null): UserLoginState()
}