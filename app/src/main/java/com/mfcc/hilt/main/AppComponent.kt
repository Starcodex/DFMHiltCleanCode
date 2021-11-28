package com.mfcc.hilt.main

import com.mfcc.hilt.core.di.NetworkHandler
import com.mfcc.hilt.core.di.module.ConstantsModule
import com.mfcc.hilt.core.di.module.CoreModule
import com.mfcc.hilt.persistence.AppDatabase
import com.mfcc.hilt.persistence.movies.CategoryDao
import com.mfcc.hilt.persistence.movies.CategoryMovieDao
import com.mfcc.hilt.persistence.movies.MovieDao
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    CoreModule::class
])
interface AppComponent : AndroidInjector<MFAPP> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: MFAPP): AppComponent
    }

    fun networkHandler() : NetworkHandler

    fun applicationContext() : MFAPP

    fun retrofit() : Retrofit

    fun logginInterceptor() : HttpLoggingInterceptor

    fun appDatabase() : AppDatabase

}