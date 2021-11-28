package com.mfcc.hilt.data_movies.wrapper


import com.mfcc.hilt.core.base.BaseWrapper
import com.mfcc.hilt.data_movies.remote.model.MovieItem
import com.mfcc.hilt.domain_movies.model.Movie
import com.mfcc.hilt.persistence.movies.MovieEntity


fun MovieEntity.mapToDomain()  = Movie(this.id!!, this.poster_path!!)
fun MovieItem.mapToDB()  = MovieEntity(
    this.id,
    this.title,
    this.overview,
    this.poster_path,
    this.original_language,
    this.release_date
)

class MovieRemoteToDBWrapper() : BaseWrapper<List<MovieItem>, List<MovieEntity>>() {
    override fun wrapElement(inVar: List<MovieItem>): List<MovieEntity> = inVar.map { it.mapToDB() }
}