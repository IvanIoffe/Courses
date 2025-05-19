package com.ioffeivan.courses.core.data.source.local

import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.common.utils.toFlowResult
import com.ioffeivan.courses.core.database.dao.CourseDao
import com.ioffeivan.courses.core.database.data_source.CourseLocalDataSource
import com.ioffeivan.courses.core.database.model.CourseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCourseLocalDataSource @Inject constructor(
    private val courseDao: CourseDao,
) : CourseLocalDataSource {

    override fun getFavouriteCourses(): Flow<Result<List<CourseEntity>>> {
        return courseDao.getFavouriteCourses().toFlowResult()
    }

    override fun getFavouriteCoursesIds(): Flow<List<Int>> {
        return courseDao.getFavouriteCoursesIds()
    }

    override suspend fun addCourseToFavourites(courseEntity: CourseEntity) {
        courseDao.insertCourse(courseEntity)
    }

    override suspend fun deleteCourseFromFavourites(courseEntity: CourseEntity) {
        courseDao.deleteCourse(courseEntity)
    }
}