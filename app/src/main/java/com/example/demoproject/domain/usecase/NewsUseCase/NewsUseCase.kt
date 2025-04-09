package com.example.demoproject.domain.usecase.NewsUseCase

import com.example.demoproject.data.network.ResultWrapper
import com.example.demoproject.domain.model.news.News
import com.example.demoproject.domain.repository.NewsRepository
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(): ResultWrapper<News> {
        return newsRepository.getNews()
    }
}