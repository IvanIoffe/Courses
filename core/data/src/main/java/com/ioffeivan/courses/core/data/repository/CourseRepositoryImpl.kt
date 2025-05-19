package com.ioffeivan.courses.core.data.repository

import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.data.mapResultData
import com.ioffeivan.courses.core.data.mapper.toCourse
import com.ioffeivan.courses.core.data.mapper.toCourseEntity
import com.ioffeivan.courses.core.data.mapper.toCourses
import com.ioffeivan.courses.core.database.data_source.CourseLocalDataSource
import com.ioffeivan.courses.core.domain.repository.CourseRepository
import com.ioffeivan.courses.core.model.Course
import com.ioffeivan.courses.core.model.Courses
import com.ioffeivan.courses.core.network.data_source.CourseRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    courseRemoteDataSource: CourseRemoteDataSource,
    private val courseLocalDataSource: CourseLocalDataSource,
) : CourseRepository {

    private val remote = courseRemoteDataSource.getCourses()
        .mapResultData(
            mapper = { coursesDto ->
                coursesDto.toCourses()
            },
            action = { coursesDto ->
                coursesDto.items
                    .filter { it.hasLike }
                    .forEach {
                        courseLocalDataSource.addCourseToFavourites(it.toCourseEntity())
                    }
            }
        )

    override fun getCourses(): Flow<Result<Courses>> {
        return combine(
            remote,
            courseLocalDataSource.getFavouriteCoursesIds(),
        ) { result, ids ->
            when (result) {
                is Result.Success -> {
                    val courses = result.data.items.map {
                        it.copy(hasLike = it.id in ids)
                    }
                    Result.Success(Courses(courses))
                }

                is Result.Error -> Result.Error(result.message)
                Result.Loading -> Result.Loading
            }
        }
    }

    override fun getFavouriteCourses(): Flow<Result<Courses>> {
        return courseLocalDataSource.getFavouriteCourses().mapResultData { courseEntityList ->
            Courses(items = courseEntityList.map { it.toCourse() })
        }
    }

    override suspend fun addCourseToFavourites(course: Course) {
        courseLocalDataSource.addCourseToFavourites(course.toCourseEntity())
    }

    override suspend fun deleteCourseFromFavourites(course: Course) {
        courseLocalDataSource.deleteCourseFromFavourites(course.toCourseEntity())
    }
}