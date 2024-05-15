package com.example.newsapplication.data.repository

import com.example.newsapplication.domain.model.NewsModel
import retrofit2.Response

interface Repository {

    suspend fun getUsNews(): Response<NewsModel>

    suspend fun getUkNews(): Response<NewsModel>

    suspend fun getCanadaNews(): Response<NewsModel>
}

