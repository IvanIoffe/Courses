package com.ioffeivan.courses.core.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val DATA_STORE_NAME = "user_data"

val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)