package com.ioffeivan.courses.feature.login.presentation.utils

object EmailValidator {
    private val regex =
        Regex("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")

    fun isValid(email: String): Boolean {
        return email.isNotEmpty() && email.matches(regex)
    }
}