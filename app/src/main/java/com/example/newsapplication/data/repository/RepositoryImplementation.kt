package com.example.newsapplication.data.repository

import com.example.newsapplication.data.api.ApiEndpoint
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.model.NewsModel
import retrofit2.Response
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val apiEndpoint: ApiEndpoint
)
: Repository {
    override suspend fun getNews(): Response<NewsModel> {
        return apiEndpoint.getNews()
    }

}