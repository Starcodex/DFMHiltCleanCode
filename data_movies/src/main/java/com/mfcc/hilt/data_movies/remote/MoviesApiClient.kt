package com.mfcc.hilt.data_movies.remote

import com.mfcc.hilt.core.util.Constants
import com.mfcc.hilt.data_movies.remote.model.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiClient {

    @GET("movie/{category}")
    fun movies(
        @Path("category") category :String,
        @Query("api_key") apikey: String = Constants.moviesApiKey): Call<MoviesResponse>

    @GET("movie/{category}")
    fun getMovies(
        @Path("category") category :String,
        @Query("api_key") apikey: String = Constants.moviesApiKey): Response<MoviesResponse>

    @GET("movie/{category}")
    fun getAllMovies(
        @Path("category") category :String,
        @Query("api_key") apikey: String = Constants.moviesApiKey): Call<MoviesResponse>

}