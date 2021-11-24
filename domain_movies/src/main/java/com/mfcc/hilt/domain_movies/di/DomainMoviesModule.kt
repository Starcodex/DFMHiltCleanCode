package com.mfcc.hilt.domain_movies.di

import com.mfcc.hilt.core.common.UseCase
import com.mfcc.hilt.domain_movies.GetMovies
import com.mfcc.hilt.domain_movies.model.Movie
import dagger.Binds
import dagger.Module

@Module
abstract class DomainMoviesModule {
    // Bind UseCase
    //@Binds
    //abstract fun bindsMoviesUseCase(useCase: GetMovies): UseCase<List<Movie>, UseCase.None>

}