package com.example.newsapplication.data.repository

import com.example.newsapplication.data.api.ApiEndpoint
import com.example.newsapplication.domain.model.NewsModel
import retrofit2.Response
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val apiEndpoint: ApiEndpoint
)
: Repository {
    override suspend fun getUsNews(): Response<NewsModel> {
        return apiEndpoint.getUsNews()
    }

    override suspend fun getUkNews(): Response<NewsModel> {
        return apiEndpoint.getUkNews()
    }

    override suspend fun getCanadaNews(): Response<NewsModel> {
        return apiEndpoint.getCanadaNews()
    }

}