package com.example.newsapplication.data.repository

import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.model.NewsModel
import retrofit2.Response

interface Repository {

    suspend fun getNews(): Response<NewsModel>
}

