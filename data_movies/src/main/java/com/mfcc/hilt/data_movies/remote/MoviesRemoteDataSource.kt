package com.mfcc.hilt.data_movies.remote

import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Resource
import com.mfcc.hilt.data_movies.remote.model.MovieItem
import com.mfcc.hilt.data_movies.remote.model.MoviesResponse
import retrofit2.Call

interface MoviesRemoteDataSource {
    suspend fun movies(category : String) : Either<Failure, List<MovieItem>>
    suspend fun getMovies(category : String): Resource<MoviesResponse>
    suspend fun getAllMovies(category : String): Call<MoviesResponse>
}