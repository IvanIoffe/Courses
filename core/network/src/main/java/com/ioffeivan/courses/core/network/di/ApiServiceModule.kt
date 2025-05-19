package com.ioffeivan.courses.core.network.di

import com.ioffeivan.courses.core.network.api_service.CourseApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {

    @Singleton
    @Provides
    fun provideCourseApiService(retrofitBuilder: Retrofit.Builder): CourseApiService {
        return retrofitBuilder
            .build()
            .create()
    }
}