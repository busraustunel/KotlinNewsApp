package com.example.newsapp.data.state

sealed class MessageState {
    object Idle:MessageState()
    object Success:MessageState()

    object UserNotFound:MessageState()
    object Error:MessageState()
}