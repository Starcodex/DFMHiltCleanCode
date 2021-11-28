package com.mfcc.hilt.data_movies.local

import androidx.lifecycle.LiveData
import com.mfcc.hilt.persistence.movies.*
import javax.inject.Inject

class MoviesLocalDataSourceImpl
@Inject constructor(
    private val movieDao: MovieDao,
    private val categoryMovieDao: CategoryMovieDao,
    private val categoryDao: CategoryDao
): MoviesLocalDataSource {

    override fun insertMovie(movieEntity: MovieEntity){
        movieDao.insertMovie(movieEntity)
    }

    override fun insertAllMovies(movies: List<MovieEntity>) {
        movieDao.insertAllMovies(movies)
    }

    override fun insertCategory(categoryEntity: CategoryEntity){
        categoryDao.insertCategory(categoryEntity)
    }

    override fun getAllMoviesByCategory(category: String): List<MovieEntity> {
        return categoryMovieDao.getAllMoviesByCategory(category)
    }

    override fun getMoviesByCategory(category : String): LiveData<List<MovieEntity>> {
        return categoryMovieDao.getMoviesByCategory(category)
    }

    override fun insertCategoryMovie(categoryMovieEntity: CategoryMovieEntity){
        categoryMovieDao.insert(categoryMovieEntity)
    }
}