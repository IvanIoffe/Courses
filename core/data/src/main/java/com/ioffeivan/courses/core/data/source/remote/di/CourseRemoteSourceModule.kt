package com.ioffeivan.courses.core.data.source.remote.di

import com.ioffeivan.courses.core.data.source.remote.RetrofitCourseRemoteDataSource
import com.ioffeivan.courses.core.network.data_source.CourseRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface CourseRemoteSourceModuleBinder {

    @Binds
    fun bindRetrofitCourseRemoteDataSource(
        retrofitCourseRemoteDataSource: RetrofitCourseRemoteDataSource
    ): CourseRemoteDataSource
}