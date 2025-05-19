package com.ioffeivan.courses.core.data.source.local.di

import com.ioffeivan.courses.core.data.source.local.RoomCourseLocalDataSource
import com.ioffeivan.courses.core.database.data_source.CourseLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface CourseLocalSourceModuleBinder {

    @Binds
    fun bindRoomCourseLocalDataSource(
        roomCourseLocalDataSource: RoomCourseLocalDataSource
    ): CourseLocalDataSource
}