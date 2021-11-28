package com.mfcc.hilt.data_movies.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mfcc.hilt.domain_movies.model.Movie

data class MovieItem(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("poster_path")
    @Expose
    var poster_path: String? = null,

    @SerializedName("original_language")
    @Expose
    var original_language: String? = null,

    @SerializedName("release_date")
    @Expose
    var release_date: String? = null
){
    fun toMovie() = Movie(this.id!!, this.poster_path!!)
}
