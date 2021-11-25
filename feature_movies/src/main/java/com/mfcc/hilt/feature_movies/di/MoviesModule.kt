package com.mfcc.hilt.feature_movies.di

import androidx.lifecycle.ViewModel
import com.mfcc.hilt.data_movies.MoviesRepositoryImpl
import com.mfcc.hilt.domain_movies.di.DomainMoviesModule
import com.mfcc.hilt.domain_movies.repository.MoviesRepository
import com.mfcc.hilt.feature_movies.list.MoviesViewModel
import com.mfcc.hilt.main.AppModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MoviesModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    // Binds Repository
    @Binds
    abstract fun bindsMoviesRepository(repositoryImpl: MoviesRepositoryImpl): MoviesRepository

}