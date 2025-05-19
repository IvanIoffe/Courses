package com.ioffeivan.courses.core.domain.usecase

import com.ioffeivan.courses.core.domain.repository.CourseRepository
import com.ioffeivan.courses.core.model.Course
import javax.inject.Inject

class DeleteCourseFromFavouritesUseCase @Inject constructor(
    private val courseRepository: CourseRepository,
) {
    suspend operator fun invoke(course: Course) {
        courseRepository.deleteCourseFromFavourites(course)
    }
}