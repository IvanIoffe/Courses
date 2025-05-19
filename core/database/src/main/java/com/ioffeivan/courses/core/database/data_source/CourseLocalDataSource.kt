package com.ioffeivan.courses.core.database.data_source

import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.database.model.CourseEntity
import kotlinx.coroutines.flow.Flow

interface CourseLocalDataSource {
    fun getFavouriteCourses(): Flow<Result<List<CourseEntity>>>
    fun getFavouriteCoursesIds(): Flow<List<Int>>
    suspend fun addCourseToFavourites(courseEntity: CourseEntity)
    suspend fun deleteCourseFromFavourites(courseEntity: CourseEntity)
}