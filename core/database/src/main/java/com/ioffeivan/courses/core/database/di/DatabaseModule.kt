package com.ioffeivan.courses.core.database.di

import android.content.Context
import androidx.room.Room
import com.ioffeivan.courses.core.database.db.CoursesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCoursesDatabase(@ApplicationContext context: Context): CoursesDatabase {
        return Room.databaseBuilder(
            context,
            CoursesDatabase::class.java,
            "courses-database"
        ).fallbackToDestructiveMigration(true).build()
    }
}