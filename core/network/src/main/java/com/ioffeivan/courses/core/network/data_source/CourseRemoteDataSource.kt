package com.ioffeivan.courses.core.network.data_source

import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.network.model.CoursesDto
import kotlinx.coroutines.flow.Flow

interface CourseRemoteDataSource {
    fun getCourses(): Flow<Result<CoursesDto>>
}