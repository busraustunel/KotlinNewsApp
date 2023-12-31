package com.example.newsapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.repo.UserRepository
import com.example.newsapp.data.state.MessageState
import com.example.newsapp.data.state.UserLoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {

    private val _userLoginState:MutableStateFlow<UserLoginState> = MutableStateFlow(UserLoginState.Idle)
    val userLoginState:StateFlow<UserLoginState> = _userLoginState

    private val _messageState: MutableSharedFlow<MessageState> = MutableSharedFlow()
    val messageState: MutableSharedFlow<MessageState> = _messageState

    fun login(email:String, password:String) {
       viewModelScope.launch {
           kotlin.runCatching {
               _userLoginState.emit(UserLoginState.Checking)
               val user = userRepository.getUser(email, password)
               user?.let {
                   _userLoginState.emit(UserLoginState.Success(it))
                   _messageState.emit(MessageState.Success)
               } ?: kotlin.run {
                   _userLoginState.emit(UserLoginState.UserNotFound)
                   _messageState.emit(MessageState.UserNotFound)
               }
              }.onFailure {
                  _userLoginState.emit(UserLoginState.Error(it))
                    _messageState.emit(MessageState.Error)
              }
       }
    }
}