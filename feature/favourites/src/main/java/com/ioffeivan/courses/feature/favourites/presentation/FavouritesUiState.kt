package com.ioffeivan.courses.feature.favourites.presentation

import com.ioffeivan.courses.core.model.Courses

sealed class FavouritesUiState {
    data object Loading : FavouritesUiState()

    data class Content(
        val courses: Courses,
    ) : FavouritesUiState()

    data object EmptyContent : FavouritesUiState()

    data class Error(val message: String) : FavouritesUiState()
}