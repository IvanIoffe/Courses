package com.ioffeivan.courses.feature.login.domain.usecase

import com.ioffeivan.courses.feature.login.domain.model.User
import com.ioffeivan.courses.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    operator fun invoke(): Flow<User?> = repository.getUser()
}