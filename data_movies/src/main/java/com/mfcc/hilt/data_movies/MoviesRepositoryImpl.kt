package com.mfcc.hilt.data_movies

import retrofit2.Call
import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Either.*
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Failure.*
import com.mfcc.hilt.core.di.NetworkHandler
import com.mfcc.hilt.data_movies.remote.MoviesApiClient
import com.mfcc.hilt.data_movies.remote.MoviesResponse
import com.mfcc.hilt.domain_movies.model.Movie
import com.mfcc.hilt.domain_movies.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service : MoviesApiClient
): MoviesRepository {

    override fun movies(genre : Int): Either<Failure, List<Movie>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                service.movies(genre, "8c0457b0a34b8e815c0e1ef2ff2aa7d1"),
                { it.results.map{ it.toMovie() } },
                MoviesResponse(emptyList())
            )
            false -> Left(NetworkConnection)
        }
    }

    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Right(transform((response.body() ?: default)))
                false -> Left(ServerError)
            }
        } catch (exception: Throwable) {
            Left(ServerError)
        }
    }

}