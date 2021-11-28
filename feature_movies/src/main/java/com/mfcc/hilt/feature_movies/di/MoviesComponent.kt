package com.mfcc.hilt.feature_movies.di

import com.mfcc.hilt.core.di.module.ConstantsModule
import com.mfcc.hilt.feature_movies.MoviesListActivity
import com.mfcc.hilt.feature_movies.list.MoviesFragment
import com.mfcc.hilt.main.AppComponent
import com.mfcc.hilt.main.AppModule
import com.mfcc.hilt.persistence.movies.CategoryDao
import com.mfcc.hilt.persistence.movies.CategoryMovieDao
import com.mfcc.hilt.persistence.movies.MovieDao
import dagger.Component


@MoviesScope
@Component(
    dependencies = arrayOf(
        AppComponent::class
    ),
    modules = arrayOf(
        MoviesModule::class
    )
)
interface MoviesComponent {

    @Component.Factory
    interface Factory {
        // Takes an instance of AppComponent when creating
        // an instance of LoginComponent
        fun create(appComponent: AppComponent): MoviesComponent
    }

    fun inject(moviesActivity : MoviesListActivity)
    fun inject(moviesFragment: MoviesFragment)

    fun movieDao() : MovieDao
    fun categoryDao() : CategoryDao
    fun categoryMovieDao() : CategoryMovieDao
}