package com.ioffeivan.courses.core.domain.repository

import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.model.Course
import com.ioffeivan.courses.core.model.Courses
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getCourses(): Flow<Result<Courses>>
    fun getFavouriteCourses(): Flow<Result<Courses>>
    suspend fun addCourseToFavourites(course: Course)
    suspend fun deleteCourseFromFavourites(course: Course)
}