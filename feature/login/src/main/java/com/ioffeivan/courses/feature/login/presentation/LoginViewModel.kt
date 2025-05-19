package com.ioffeivan.courses.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.courses.feature.login.domain.model.User
import com.ioffeivan.courses.feature.login.domain.usecase.GetUserUseCase
import com.ioffeivan.courses.feature.login.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    getUserUseCase: GetUserUseCase,
) : ViewModel() {

    val uiState: Flow<LoginUiState> = getUserUseCase()
        .map {
            handleUser(it)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            initialValue = LoginUiState.Loading,
        )


    fun saveUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserUseCase(user)
        }
    }

    private fun handleUser(user: User?): LoginUiState {
        return if (user == null) LoginUiState.NotLogged else LoginUiState.Logged
    }
}