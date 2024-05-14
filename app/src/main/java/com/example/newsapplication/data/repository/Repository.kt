package com.example.newsapplication.data.repository

import com.example.newsapplication.domain.model.Article
import retrofit2.Response

interface Repository {

    suspend fun getNews(): Response<List<Article>>
}