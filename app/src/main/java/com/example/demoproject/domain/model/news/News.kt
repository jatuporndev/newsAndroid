package com.example.demoproject.domain.model.news

data class News(
    val articles: List<Article>,
    val status: String? = null,
    val totalResults: Int = 0
)