package com.mfcc.hilt.feature_movies.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfcc.hilt.core.extensions.inflate
import com.mfcc.hilt.feature_movies.R
import javax.inject.Inject
import javax.inject.Named
import kotlin.properties.Delegates

class MoviesAdapter
@Inject constructor() : RecyclerView.Adapter<MoviesViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (MovieView) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(parent.inflate(R.layout.row_movie))


    override fun getItemCount() = collection.size


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(collection[position])
    }

}