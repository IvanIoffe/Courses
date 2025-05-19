package com.ioffeivan.courses.core.network.model

import com.google.gson.annotations.SerializedName

data class CoursesDto(
    @SerializedName("courses")
    val items: List<CourseDto>,
)