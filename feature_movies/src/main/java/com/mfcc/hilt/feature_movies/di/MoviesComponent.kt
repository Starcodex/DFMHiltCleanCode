package com.mfcc.hilt.feature_movies.di

import com.mfcc.hilt.feature_movies.MoviesListActivity
import com.mfcc.hilt.feature_movies.list.MoviesFragment
import com.mfcc.hilt.main.AppComponent
import dagger.Component


@MoviesScope
@Component(
    dependencies = [AppComponent::class],
    modules = [MoviesModule::class]
)
interface MoviesComponent {
    fun inject(moviesActivity : MoviesListActivity)
    fun inject(moviesFragment: MoviesFragment)
}