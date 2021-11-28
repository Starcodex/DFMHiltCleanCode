package com.mfcc.hilt.domain_movies.repository

import androidx.lifecycle.LiveData
import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Resource
import com.mfcc.hilt.domain_movies.model.Movie

interface MoviesRepository {
    suspend fun movies(category : String): Either<Failure, List<Movie>>
    fun getMovies(category: String): LiveData<Resource<List<Movie>>>
    suspend fun getAllMovies(category: String): Either<Failure, List<Movie>>
}