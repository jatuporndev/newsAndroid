package com.example.demoproject.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject.data.network.error
import com.example.demoproject.data.network.success
import com.example.demoproject.domain.usecase.NewsUseCase.NewsTopHeadlineUseCase
import com.example.demoproject.domain.usecase.NewsUseCase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val newsTopHeadline: NewsTopHeadlineUseCase
): ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state

    private val _stateHeadline = MutableStateFlow<HeadlineState>(HeadlineState.Loading)
    val stateHeadline: StateFlow<HeadlineState> = _stateHeadline

    fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = HomeState.Loading
            newsUseCase.invoke().success {
                _state.value = HomeState.Success(it)
            }.error {
                _state.value = HomeState.Error(it.message)
            }
        }
    }

    fun fetchHeadlineTechnology() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateHeadline.value = HeadlineState.Loading
            newsTopHeadline.invoke("technology").success {
                _stateHeadline.value = HeadlineState.Success(1, it)
            }.error {
                _stateHeadline.value = HeadlineState.Error(it.message)
            }
        }
    }

    fun fetchHeadlineSport() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateHeadline.value = HeadlineState.Loading
            newsTopHeadline.invoke("sport").success {
                _stateHeadline.value = HeadlineState.Success(2, it)
            }.error {
                _stateHeadline.value = HeadlineState.Error(it.message)
            }
        }
    }


}