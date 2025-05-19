package com.ioffeivan.courses.core.database.di

import com.ioffeivan.courses.core.database.dao.CourseDao
import com.ioffeivan.courses.core.database.db.CoursesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DaoModule {

    @Singleton
    @Provides
    fun provideCourseDao(
        coursesDatabase: CoursesDatabase
    ): CourseDao = coursesDatabase.courseDao()
}