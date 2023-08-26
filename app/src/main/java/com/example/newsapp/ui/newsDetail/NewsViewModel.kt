package com.example.newsapp.ui.newsDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.impl.NewsRepositoryImpl
import com.example.newsapp.data.state.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(private val newsRepository: NewsRepositoryImpl) : ViewModel() {

    private val _newsState: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Idle)
    val newsState: StateFlow<NewsState> = _newsState

    fun getNews() {
        viewModelScope.launch {
            _newsState.emit(NewsState.Loading)
            kotlin.runCatching {
                val news = newsRepository.getNewsDetail()
                _newsState.emit(NewsState.Success(news))
            }.onFailure {
                _newsState.emit(NewsState.Error(it))
            }
        }
    }
}
