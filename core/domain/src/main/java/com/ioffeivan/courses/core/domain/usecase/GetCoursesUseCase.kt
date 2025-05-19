package com.ioffeivan.courses.core.domain.usecase

import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.domain.repository.CourseRepository
import com.ioffeivan.courses.core.model.Courses
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val coursesRepository: CourseRepository,
) {
    operator fun invoke(): Flow<Result<Courses>> = coursesRepository.getCourses()
}