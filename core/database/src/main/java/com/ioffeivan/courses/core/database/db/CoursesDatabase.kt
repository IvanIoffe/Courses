package com.ioffeivan.courses.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ioffeivan.courses.core.database.converter.LocalDateConverter
import com.ioffeivan.courses.core.database.dao.CourseDao
import com.ioffeivan.courses.core.database.model.CourseEntity

@Database(entities = [CourseEntity::class], version = 3, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class CoursesDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}