package com.example.demoproject.presentation.ui.home

import com.example.demoproject.domain.model.news.News

sealed class HomeState {
    data object Loading : HomeState()
    data class Success(val data: News) : HomeState()
    data class Error(val message: String) : HomeState()
}

sealed class HeadlineState {
    data object Loading : HeadlineState()
    data class Success(val section: Int, val data: News) : HeadlineState()
    data class Error(val message: String) : HeadlineState()
}