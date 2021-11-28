package com.mfcc.hilt.data_movies.remote

import com.mfcc.hilt.core.base.BaseDataSource
import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Resource
import com.mfcc.hilt.core.di.NetworkHandler
import com.mfcc.hilt.data_movies.remote.model.MovieItem
import com.mfcc.hilt.data_movies.remote.model.MoviesResponse
import retrofit2.Call
import javax.inject.Inject

class MoviesRemoteDataSourceImpl
    @Inject constructor(
        var apiClient: MoviesApiClient,
        networkHandler: NetworkHandler
    ): BaseDataSource(networkHandler), MoviesRemoteDataSource {

    override suspend fun movies(category : String): Either<Failure, List<MovieItem>> {
        return request(
                apiClient.movies(category),
                { it.results },
                MoviesResponse(emptyList())
            )
    }

    override suspend fun getMovies(category : String): Resource<MoviesResponse> =
        getResult { apiClient.getMovies(category) }

    override suspend fun getAllMovies(category : String): Call<MoviesResponse> =
        apiClient.getAllMovies(category)
}