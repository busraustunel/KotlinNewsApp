package com.example.newsapp.data.state

sealed class UserRegisterState {
    object Idle:UserRegisterState()
    object Checking:UserRegisterState()
    object Success:UserRegisterState()
    class Error(val throwable: Throwable? = null): UserRegisterState()
}