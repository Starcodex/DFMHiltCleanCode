package com.mfcc.hilt.persistence.movies

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryMovie: CategoryMovieEntity)

    @Query(" SELECT MOVIES.* FROM MOVIES INNER JOIN CATEGORY_MOVIES ON MOVIES.id = CATEGORY_MOVIES.movie_id WHERE CATEGORY_MOVIES.category_name = :category ")
    fun getMoviesByCategory(category: String): LiveData<List<MovieEntity>>

    @Query(" SELECT MOVIES.* FROM MOVIES INNER JOIN CATEGORY_MOVIES ON MOVIES.id = CATEGORY_MOVIES.movie_id WHERE CATEGORY_MOVIES.category_name = :category ")
    fun getAllMoviesByCategory(category: String): List<MovieEntity>

}