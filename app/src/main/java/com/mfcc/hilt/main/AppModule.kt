package com.mfcc.hilt.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.mfcc.hilt.core.di.ViewModelFactory
import com.mfcc.hilt.core.util.Constants
import com.mfcc.hilt.persistence.AppDatabase
import com.mfcc.hilt.persistence.movies.CategoryDao
import com.mfcc.hilt.persistence.movies.CategoryMovieDao
import com.mfcc.hilt.persistence.movies.MovieDao
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun provideAppContext(app: MFAPP) : Context

    companion object {

        @Provides
        fun providesAppDatabase(app: MFAPP): AppDatabase =
            Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, Constants.dbName)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

    }

}