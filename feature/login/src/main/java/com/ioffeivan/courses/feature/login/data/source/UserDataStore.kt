package com.ioffeivan.courses.feature.login.data.source

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ioffeivan.courses.core.datastore.dataStore
import com.ioffeivan.courses.feature.login.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStore @Inject constructor(
    private val context: Context,
) {
    companion object {
        val EMAIL_KEY = stringPreferencesKey("user_email")
    }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL_KEY] = user.email
        }
    }

    fun getUser(): Flow<User?> = context.dataStore.data.map { prefs ->
        val email = prefs[EMAIL_KEY]
        if (email != null) User(email) else null
    }
}