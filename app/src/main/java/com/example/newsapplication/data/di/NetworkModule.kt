package com.example.newsapplication.data.di

import com.example.newsapplication.data.api.ApiEndpoint
import com.example.newsapplication.data.repository.Repository
import com.example.newsapplication.data.repository.RepositoryImplementation
import com.example.newsapplication.util.Constant
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    @Provides
    @Singleton
    fun gsonCoverterFacgtory(
        gson: Gson
    ) = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun apiInstance(retrofit: Retrofit): ApiEndpoint = retrofit.create(ApiEndpoint::class.java)

    @Provides
    fun providesRepository(apiEndpoint: ApiEndpoint): Repository {
        return RepositoryImplementation(apiEndpoint)
    }

}