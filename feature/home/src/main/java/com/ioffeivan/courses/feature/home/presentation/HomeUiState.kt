package com.ioffeivan.courses.feature.home.presentation

import com.ioffeivan.courses.core.model.Courses

sealed class HomeUiState {
    data object Loading : HomeUiState()

    data class Content(
        val courses: Courses,
    ) : HomeUiState()

    data object NotSortedCourses : HomeUiState()

    data class Error(val message: String) : HomeUiState()
}