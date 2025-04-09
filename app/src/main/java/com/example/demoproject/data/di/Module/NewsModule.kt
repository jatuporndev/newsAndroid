package com.example.demoproject.data.di.Module

import com.example.demoproject.data.network.ApiService.News.NewsService
import com.example.demoproject.data.repository.NewsRepositoryImpl
import com.example.demoproject.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsModule {

    @Provides
    @Singleton
    fun providerNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun providerNewsRepositoryImpl(newsService: NewsService): NewsRepository {
        return NewsRepositoryImpl(newsService)
    }

}