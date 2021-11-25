package com.mfcc.hilt.domain_movies.repository

import com.mfcc.hilt.core.common.Either
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.domain_movies.model.Movie

interface MoviesRepository {
    fun movies(genre : Int): Either<Failure, List<Movie>>
}