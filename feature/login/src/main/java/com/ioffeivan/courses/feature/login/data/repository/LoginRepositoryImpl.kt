package com.ioffeivan.courses.feature.login.data.repository

import com.ioffeivan.courses.feature.login.data.source.UserDataStore
import com.ioffeivan.courses.feature.login.domain.model.User
import com.ioffeivan.courses.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStore: UserDataStore,
) : LoginRepository {

    override suspend fun saveUser(user: User) = dataStore.saveUser(user)

    override fun getUser(): Flow<User?> = dataStore.getUser()
}