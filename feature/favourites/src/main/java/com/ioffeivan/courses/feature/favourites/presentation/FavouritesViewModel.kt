package com.ioffeivan.courses.feature.favourites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.courses.core.common.Result
import com.ioffeivan.courses.core.domain.usecase.DeleteCourseFromFavouritesUseCase
import com.ioffeivan.courses.core.domain.usecase.GetFavouriteCoursesUseCase
import com.ioffeivan.courses.core.model.Course
import com.ioffeivan.courses.core.model.Courses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    getFavouriteCoursesUseCase: GetFavouriteCoursesUseCase,
    private val deleteCourseFromFavouritesUseCase: DeleteCourseFromFavouritesUseCase,
) : ViewModel() {
    val uiState: StateFlow<FavouritesUiState> = getFavouriteCoursesUseCase()
        .map {
            handleResult(it)
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FavouritesUiState.Loading,
        )

    fun deleteCourseFromFavourites(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCourseFromFavouritesUseCase(course)
        }
    }

    private fun handleResult(result: Result<Courses>): FavouritesUiState {
        return when (result) {
            Result.Loading -> FavouritesUiState.Loading
            is Result.Error -> FavouritesUiState.Error(result.message)
            is Result.Success -> {
                val courses = result.data
                if (courses.items.isEmpty()) {
                    FavouritesUiState.EmptyContent
                } else {
                    FavouritesUiState.Content(courses = courses)
                }
            }
        }
    }
}