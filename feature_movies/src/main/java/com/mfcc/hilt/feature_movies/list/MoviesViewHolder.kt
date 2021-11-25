package com.mfcc.hilt.feature_movies.list

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mfcc.hilt.core.base.BaseBindingHolder
import com.mfcc.hilt.core.extensions.loadFromUrl
import com.mfcc.hilt.core.util.Constants
import com.mfcc.hilt.feature_movies.databinding.RowMovieBinding

class MoviesViewHolder (viewItem : View) : BaseBindingHolder<RowMovieBinding, MovieView>(viewItem) {

    lateinit var movie : MovieView


    override fun bind(params: MovieView) {
        this.movie = params
        binding.viewHolder = this
    }

    val imageUrl: String
        get() = Constants.urlImgBack+movie.poster


    object ImageBindingAdapter {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageUrl(view: ImageView, url: String) = view.loadFromUrl(url)
    }

}