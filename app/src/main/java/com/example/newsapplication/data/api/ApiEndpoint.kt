package com.example.newsapplication.data.api

import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.model.NewsModel
import com.example.newsapplication.util.Constant
import retrofit2.Response
import retrofit2.http.GET

interface ApiEndpoint {

    @GET(Constant.NEWS_ENDPOINT)
    suspend fun getNews(): Response<NewsModel>

}