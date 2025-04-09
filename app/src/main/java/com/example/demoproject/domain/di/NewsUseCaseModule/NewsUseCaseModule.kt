package com.example.demoproject.domain.di.NewsUseCaseModule

import com.example.demoproject.domain.repository.NewsRepository
import com.example.demoproject.domain.usecase.NewsUseCase.NewsTopHeadlineUseCase
import com.example.demoproject.domain.usecase.NewsUseCase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsUseCaseModule {

    @Provides
    @Singleton
    fun newsUseCaseProvider(newsRepository: NewsRepository) : NewsUseCase {
        return NewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun topHeadlineUseCaseProvider(newsRepository: NewsRepository) : NewsTopHeadlineUseCase {
        return NewsTopHeadlineUseCase(newsRepository)
    }
}