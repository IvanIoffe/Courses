package com.ioffeivan.courses.core.data.di

import com.ioffeivan.courses.core.data.repository.CourseRepositoryImpl
import com.ioffeivan.courses.core.domain.repository.CourseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModuleBinder {

    @Binds
    fun bindCourseRepositoryImpl(
        courseRepositoryImpl: CourseRepositoryImpl
    ): CourseRepository
}