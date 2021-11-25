package com.mfcc.hilt.data_movies.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiClient {
    @GET("genre/{genre}/movies")
    fun movies(@Path("genre") genre:Int, @Query("api_key") apikey:String): Call<MoviesResponse>
}