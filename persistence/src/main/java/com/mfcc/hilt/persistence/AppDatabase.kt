package com.mfcc.hilt.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mfcc.hilt.persistence.movies.*

@Database(entities = arrayOf(
    MovieEntity::class,
    CategoryEntity::class,
    CategoryMovieEntity::class
), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun categoryDao(): CategoryDao

    abstract fun categoryMovieDao(): CategoryMovieDao

}