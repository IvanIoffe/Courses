package com.ioffeivan.courses.feature.login.domain.usecase

import com.ioffeivan.courses.feature.login.domain.model.User
import com.ioffeivan.courses.feature.login.domain.repository.LoginRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(user: User) = repository.saveUser(user)
}