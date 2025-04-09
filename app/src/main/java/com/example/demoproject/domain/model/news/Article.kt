package com.example.demoproject.domain.model.news

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source? = null,
    val title: String,
    val url: String,
    val urlToImage: String
)