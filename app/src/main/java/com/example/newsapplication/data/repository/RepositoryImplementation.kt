package com.example.newsapplication.data.repository

import com.example.newsapplication.data.api.ApiEndpoint
import com.example.newsapplication.domain.model.Article
import retrofit2.Response
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val apiEndpoint: ApiEndpoint
)
: Repository {
    override suspend fun getNews(): Response<List<Article>> {
        return apiEndpoint.getNews()
    }

}