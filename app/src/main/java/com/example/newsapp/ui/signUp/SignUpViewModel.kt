package com.example.newsapp.ui.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.database.entity.User
import com.example.newsapp.data.repository.repo.UserRepository
import com.example.newsapp.data.state.MessageState
import com.example.newsapp.data.state.UserRegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
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

    private val _messageState: MutableSharedFlow<MessageState> = MutableSharedFlow()
    val messageState: MutableSharedFlow<MessageState> = _messageState

    fun insertUser(name: String, surname: String, email: String, password: String, passwordAgain: String) {
        if (name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordAgain.isNotEmpty()) {
            if (password == passwordAgain) {
                viewModelScope.launch(Dispatchers.IO) {
                    var result: Long = -1
                    val user = User(name = name, surname = surname, email = email, password = password)
                    kotlin.runCatching {
                        _registerState.emit(UserRegisterState.Checking)
                        result = userRepository.insert(user)
                    }.onSuccess {
                        _registerState.value = UserRegisterState.Success(result.toInt())
                        _messageState.emit(MessageState.Success)
                    }.onFailure {
                        _registerState.value = UserRegisterState.Error(it)
                        _messageState.emit(MessageState.Error)
                    }
            }
            } else {
                _registerState.value = UserRegisterState.InputError
            }
        }
    }


}