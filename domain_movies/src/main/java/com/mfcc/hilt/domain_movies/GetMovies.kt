package com.mfcc.hilt.domain_movies

import com.mfcc.hilt.core.common.UseCase
import com.mfcc.hilt.domain_movies.model.Movie
import com.mfcc.hilt.domain_movies.repository.MoviesRepository
import javax.inject.Inject

class GetMovies @Inject constructor (val moviesRepository: MoviesRepository)
    : UseCase<List<Movie>, UseCase.None>() {
    override suspend fun run(params: None) = moviesRepository.movies()
}