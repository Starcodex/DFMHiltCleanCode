package com.mfcc.hilt.feature_movies.di

import androidx.lifecycle.ViewModel
import com.mfcc.hilt.data_movies.MoviesRepositoryImpl
import com.mfcc.hilt.data_movies.local.MoviesLocalDataSource
import com.mfcc.hilt.data_movies.local.MoviesLocalDataSourceImpl
import com.mfcc.hilt.data_movies.remote.MoviesApiClient
import com.mfcc.hilt.data_movies.remote.MoviesRemoteDataSource
import com.mfcc.hilt.data_movies.remote.MoviesRemoteDataSourceImpl
import com.mfcc.hilt.domain_movies.repository.MoviesRepository
import com.mfcc.hilt.feature_movies.list.MoviesViewModel
import com.mfcc.hilt.persistence.AppDatabase
import com.mfcc.hilt.persistence.movies.CategoryDao
import com.mfcc.hilt.persistence.movies.CategoryMovieDao
import com.mfcc.hilt.persistence.movies.MovieDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit


@Module
abstract class MoviesModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    // Binds Repository
    @Binds
    abstract fun bindsMoviesRepository(repositoryImpl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun bindMoviesRemoteDataSource(remoteImpl: MoviesRemoteDataSourceImpl) : MoviesRemoteDataSource

    @Binds
    abstract fun bindMoviesLocalDataSource(localImpl: MoviesLocalDataSourceImpl) : MoviesLocalDataSource


    companion object {


        @Provides
        fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
            return appDatabase.movieDao()
        }

        @Provides
        fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
            return appDatabase.categoryDao()
        }

        @Provides
        fun provideCategoryMovieDao(appDatabase: AppDatabase): CategoryMovieDao {
            return appDatabase.categoryMovieDao()
        }

        @Provides
        fun provideMoviesApiClient(retrofit : Retrofit) = retrofit.create(
            MoviesApiClient::class.java)

    }
}