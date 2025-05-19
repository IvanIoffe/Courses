package com.ioffeivan.courses.core.data.source.remote

import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.network.api_service.CourseApiService
import com.ioffeivan.courses.core.network.data_source.CourseRemoteDataSource
import com.ioffeivan.courses.core.network.model.CoursesDto
import com.ioffeivan.courses.core.network.remoteRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitCourseRemoteDataSource @Inject constructor(
    private val courseApiService: CourseApiService,
) : CourseRemoteDataSource {

    override fun getCourses(): Flow<Result<CoursesDto>> {
        return remoteRequestFlow {
            courseApiService.getCourses()
        }
    }
}