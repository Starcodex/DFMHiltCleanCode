package com.mfcc.hilt.data_movies.local

import androidx.lifecycle.LiveData
import com.mfcc.hilt.persistence.movies.CategoryEntity
import com.mfcc.hilt.persistence.movies.CategoryMovieEntity
import com.mfcc.hilt.persistence.movies.MovieEntity

interface MoviesLocalDataSource {

    fun insertMovie(movieEntity: MovieEntity)

    fun insertAllMovies(movies : List<MovieEntity>)

    fun insertCategory(categoryEntity: CategoryEntity)

    fun getAllMoviesByCategory(category : String): List<MovieEntity>

    fun getMoviesByCategory(category : String): LiveData<List<MovieEntity>>

    fun insertCategoryMovie(categoryMovieEntity: CategoryMovieEntity)
}