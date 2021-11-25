package com.mfcc.hilt.data_movies.di


import com.mfcc.hilt.data_movies.MoviesRepositoryImpl
import com.mfcc.hilt.data_movies.remote.MoviesApiClient
import com.mfcc.hilt.domain_movies.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataMoviesModule {

    @Provides
    fun provideMoviesApiClient(retrofit : Retrofit) = retrofit.create(
        MoviesApiClient::class.java)

}