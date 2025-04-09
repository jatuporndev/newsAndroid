package com.example.demoproject.data.repository

import com.example.demoproject.data.network.ApiService.News.NewsService
import com.example.demoproject.data.network.ResultWrapper
import com.example.demoproject.domain.model.news.News
import com.example.demoproject.domain.repository.NewsRepository

import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsService: NewsService) :
    NewsRepository {

    override suspend fun getNews(): ResultWrapper<News> {
        val response = newsService.getNews()
        return if (response.isSuccessful) {
            response.body()?.let {
                ResultWrapper.Success(it)
            } ?: ResultWrapper.Error("Empty body", response.code())
        } else {
            ResultWrapper.Error(response.errorBody()?.string().toString(), response.code())
        }

    }

    override suspend fun getTopHeadline(category: String): ResultWrapper<News> {
        val response = newsService.getTopHeadline(category)
        return if (response.isSuccessful) {
            response.body()?.let {
                ResultWrapper.Success(it)
            } ?: ResultWrapper.Error("Empty body", response.code())
        } else {
            ResultWrapper.Error(response.errorBody()?.string().toString(), response.code())
        }
    }
}