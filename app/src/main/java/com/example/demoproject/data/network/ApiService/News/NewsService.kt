package com.example.demoproject.data.network.ApiService.News

import com.example.demoproject.domain.model.news.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("everything?q=thailand&sortBy=publishedAt")
    suspend fun getNews(): Response<News>

    @GET("top-headlines")
    suspend fun getTopHeadline(@Query("category") category: String): Response<News>
}