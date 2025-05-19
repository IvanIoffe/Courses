package com.ioffeivan.courses.feature.login.data.di

import com.ioffeivan.courses.feature.login.data.repository.LoginRepositoryImpl
import com.ioffeivan.courses.feature.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface LoginModule {

    @Binds
    fun bindLoginRepositoryImpl(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}