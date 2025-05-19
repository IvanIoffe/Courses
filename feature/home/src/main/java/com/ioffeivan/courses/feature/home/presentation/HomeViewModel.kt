package com.ioffeivan.courses.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.common.utils.mergeWith
import com.ioffeivan.courses.core.domain.usecase.AddCourseToFavouritesUseCase
import com.ioffeivan.courses.core.domain.usecase.DeleteCourseFromFavouritesUseCase
import com.ioffeivan.courses.core.domain.usecase.GetCoursesUseCase
import com.ioffeivan.courses.core.model.Course
import com.ioffeivan.courses.core.model.Courses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCoursesUseCase: GetCoursesUseCase,
    private val addCourseToFavouritesUseCase: AddCourseToFavouritesUseCase,
    private val deleteCourseFromFavouritesUseCase: DeleteCourseFromFavouritesUseCase,
) : ViewModel() {
    private val _sortedCourseUiState = MutableStateFlow<HomeUiState>(HomeUiState.NotSortedCourses)
    val uiState: StateFlow<HomeUiState> = getCoursesUseCase()
        .onStart {
            _sortedCourseUiState.value = HomeUiState.NotSortedCourses
        }
        .map {
            handleResult(it)
        }
        .mergeWith(_sortedCourseUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading,
        )

    fun sortCoursesBy(sortType: SortType) {
        val currentState = uiState.value
        if (currentState is HomeUiState.Content) {
            val sorted = currentState.courses.items.sortedByDescending {
                when (sortType) {
                    SortType.PUBLISH_DATE -> it.publishDate
                }
            }
            _sortedCourseUiState.value = HomeUiState.Content(Courses(sorted))
        }
    }

    fun addCourseToFavourites(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            addCourseToFavouritesUseCase(course.copy(hasLike = true))
        }
    }

    fun deleteCourseFromFavourites(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCourseFromFavouritesUseCase(course)
        }
    }

    private fun handleResult(result: Result<Courses>): HomeUiState {
        return when (result) {
            Result.Loading -> HomeUiState.Loading
            is Result.Error -> HomeUiState.Error(result.message)
            is Result.Success -> HomeUiState.Content(courses = result.data)
        }
    }
}

enum class SortType {
    PUBLISH_DATE
}