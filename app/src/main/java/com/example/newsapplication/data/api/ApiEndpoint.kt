package com.example.newsapplication.data.api

import com.example.newsapplication.domain.model.NewsModel
import com.example.newsapplication.util.Constant
import retrofit2.Response
import retrofit2.http.GET

interface ApiEndpoint {

    @GET(Constant.NEWS_ENDPOINT_US)
    suspend fun getUsNews(): Response<NewsModel>

    @GET(Constant.NEWS_ENDPOINT_UK)
    suspend fun getUkNews(): Response<NewsModel>

    @GET(Constant.NEWS_ENDPOINT_CANADA)
    suspend fun getCanadaNews(): Response<NewsModel>

}