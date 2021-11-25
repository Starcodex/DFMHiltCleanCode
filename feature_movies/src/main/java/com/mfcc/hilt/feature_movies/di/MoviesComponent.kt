package com.mfcc.hilt.feature_movies.di

import com.mfcc.hilt.core.di.module.ConstantsModule
import com.mfcc.hilt.data_movies.di.DataMoviesModule
import com.mfcc.hilt.feature_movies.MoviesListActivity
import com.mfcc.hilt.feature_movies.list.MoviesFragment
import com.mfcc.hilt.main.AppComponent
import dagger.Component


@MoviesScope
@Component(
    dependencies = arrayOf(
        AppComponent::class
    ),
    modules = arrayOf(
        MoviesModule::class,
        DataMoviesModule::class
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
}