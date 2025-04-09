package com.example.demoproject.domain.repository

import com.example.demoproject.data.network.ResultWrapper
import com.example.demoproject.domain.model.news.News

interface NewsRepository {
    suspend fun getNews() : ResultWrapper<News>
    suspend fun getTopHeadline(category: String): ResultWrapper<News>
}