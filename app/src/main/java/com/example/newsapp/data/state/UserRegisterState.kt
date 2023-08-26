package com.example.newsapp.data.state

sealed class UserRegisterState {
    object Idle:UserRegisterState()
    object Checking:UserRegisterState()

    object InputError:UserRegisterState()
    class Success(val id:Int? = null):UserRegisterState()
    class Error(val throwable: Throwable? = null): UserRegisterState()
}