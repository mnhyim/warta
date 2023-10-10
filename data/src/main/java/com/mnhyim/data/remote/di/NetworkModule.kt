package com.mnhyim.data.remote.di

import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.data.BuildConfig
import com.mnhyim.data.local.database.AppDatabase
import com.mnhyim.data.remote.NewsApi
import com.mnhyim.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_LINK + BuildConfig.API_VERSION)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi, database: AppDatabase): NewsRepository {
        return NewsRepositoryImpl(api, database)
    }
}