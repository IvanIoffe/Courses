package com.ioffeivan.courses.feature.login.presentation

sealed class LoginUiState {
    data object Loading : LoginUiState()
    data object Logged : LoginUiState()
    data object NotLogged : LoginUiState()
}