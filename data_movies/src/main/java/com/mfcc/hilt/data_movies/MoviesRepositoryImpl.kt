package com.mfcc.hilt.data_movies

import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.di.NetworkHandler
import com.mfcc.hilt.domain_movies.model.Movie
import com.mfcc.hilt.domain_movies.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    //private val networkHandler: NetworkHandler
): MoviesRepository {

    override fun movies(): Either<Failure, List<Movie>> {
        return when (true) {
            true -> Either.Left(Failure.NetworkConnection)
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

}