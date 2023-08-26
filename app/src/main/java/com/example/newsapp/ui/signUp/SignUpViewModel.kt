package com.example.newsapp.ui.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.database.entity.User
import com.example.newsapp.data.database.repository.UserRepository
import com.example.newsapp.data.state.UserRegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _registerState: MutableStateFlow<UserRegisterState> =
        MutableStateFlow(UserRegisterState.Idle)
    var registerState: StateFlow<UserRegisterState> = _registerState

    fun insert(
        name: String,
        surname: String,
        email: String,
        password: String,
        passwordAgain: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = -1
            val user = User(name = name, surname = surname, email = email, password = password)
            kotlin.runCatching {
                _registerState.emit(UserRegisterState.Checking)
                userRepository.insert(user)
            }.onSuccess {
                _registerState.emit(UserRegisterState.Success)
            }.onFailure {
                _registerState.emit(UserRegisterState.Error())
            }


        }

    }


}