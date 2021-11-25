package com.mfcc.hilt.domain_movies

import com.mfcc.hilt.core.common.UseCase
import com.mfcc.hilt.domain_movies.model.Movie
import com.mfcc.hilt.domain_movies.repository.MoviesRepository
import javax.inject.Inject

class GetMovies @Inject constructor (val moviesRepository: MoviesRepository)
    : UseCase<List<Movie>, GetMovies.Params>() {
    override suspend fun run(params: Params) = moviesRepository.movies(params.genre)

    data class Params(val genre: Int)
}