package com.ioffeivan.courses.core.data.mapper

import com.ioffeivan.courses.core.database.model.CourseEntity
import com.ioffeivan.courses.core.model.Course
import com.ioffeivan.courses.core.model.Courses
import com.ioffeivan.courses.core.network.model.CourseDto
import com.ioffeivan.courses.core.network.model.CoursesDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun CoursesDto.toCourses() = Courses(items = items.map { it.toCourse() })

fun CourseDto.toCourse() = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = formatStartDate(startDate),
    hasLike = hasLike,
    publishDate = LocalDate.parse(publishDate, dateTimeFormatter),
)

fun CourseDto.toCourseEntity() = CourseEntity(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = formatStartDate(startDate),
    hasLike = hasLike,
    publishDate = LocalDate.parse(publishDate, dateTimeFormatter),
)

fun CourseEntity.toCourse() = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate,
)

fun Course.toCourseEntity() =
    CourseEntity(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate,
    )

private fun formatStartDate(startDate: String): String {
    val localDate = LocalDate.parse(startDate, dateTimeFormatter)
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault())
    return localDate.format(formatter)
}