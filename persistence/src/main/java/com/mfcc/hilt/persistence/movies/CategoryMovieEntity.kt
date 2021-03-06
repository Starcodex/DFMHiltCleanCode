package com.mfcc.hilt.persistence.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "category_movies",
    indices = arrayOf(Index("category_name"), Index("movie_id")),
    primaryKeys = arrayOf("category_name","movie_id"),
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["name"],
            childColumns = ["category_name"]
        ), ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"])]
)
class CategoryMovieEntity (
    @ColumnInfo(name = "category_name")
    var category_name: String = "",
    @ColumnInfo(name = "movie_id")
    var movie_id: Int = 0
)