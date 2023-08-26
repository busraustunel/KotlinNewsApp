package com.example.newsapp.ui.login

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.database.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {




}