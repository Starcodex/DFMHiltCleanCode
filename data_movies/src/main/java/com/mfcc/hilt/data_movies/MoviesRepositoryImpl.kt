package com.mfcc.hilt.data_movies


import androidx.lifecycle.map
import com.mfcc.hilt.core.base.BaseRepository
import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.data_movies.local.MoviesLocalDataSource
import com.mfcc.hilt.data_movies.wrapper.mapToDomain
import com.mfcc.hilt.data_movies.remote.MoviesRemoteDataSource
import com.mfcc.hilt.data_movies.wrapper.MovieRemoteToDBWrapper
import com.mfcc.hilt.domain_movies.model.Movie
import com.mfcc.hilt.domain_movies.repository.MoviesRepository
import com.mfcc.hilt.persistence.movies.CategoryEntity
import com.mfcc.hilt.persistence.movies.CategoryMovieEntity
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
): BaseRepository(), MoviesRepository {


    override suspend fun movies(category: String): Either<Failure, List<Movie>> =
        performGet(
            databaseQuery = { moviesLocalDataSource.getAllMoviesByCategory(category) },
            networkCall = { moviesRemoteDataSource.getMovies(category) },
            wrapperFunction = { it.map { it.mapToDomain() } },
            saveCallResult = {
                moviesLocalDataSource.insertCategory(CategoryEntity(category))
                moviesLocalDataSource.insertAllMovies(MovieRemoteToDBWrapper().wrapElement(it.results))
            }
        )

    override suspend fun getAllMovies(category: String): Either<Failure, List<Movie>> =
        performGetRemoteDBFunction(
            databaseQuery = { moviesLocalDataSource.getAllMoviesByCategory(category) },
            networkCall = { moviesRemoteDataSource.getAllMovies(category) },
            wrapperFunction = { it.map { it.mapToDomain() } },
            saveCallResult = {
                moviesLocalDataSource.insertCategory(CategoryEntity(category))
                moviesLocalDataSource.insertAllMovies(MovieRemoteToDBWrapper().wrapElement(it.results))
                it.results.map {
                    moviesLocalDataSource.insertCategoryMovie(CategoryMovieEntity(category,it.id ?: 0))
                }
            }
        )


    override fun getMovies(category: String) = performGetOperation(
        databaseQuery = { moviesLocalDataSource.getMoviesByCategory(category).map { it.map { it.mapToDomain() } } },
        networkCall = { moviesRemoteDataSource.getMovies(category) },
        saveCallResult = {
            moviesLocalDataSource.insertCategory(CategoryEntity(category))
            moviesLocalDataSource.insertAllMovies(MovieRemoteToDBWrapper().wrapElement(it.results))
            it.results.map {
                moviesLocalDataSource.insertCategoryMovie(CategoryMovieEntity(category,it.id ?: 0))
            }
        }
    )

}