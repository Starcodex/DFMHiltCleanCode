package com.mfcc.hilt.feature_movies.list



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mfcc.hilt.core.base.BaseViewModel
import com.mfcc.hilt.core.common.UseCase.*
import com.mfcc.hilt.domain_movies.GetMovies
import com.mfcc.hilt.domain_movies.GetMovies.*
import com.mfcc.hilt.domain_movies.model.Movie
import com.mfcc.hilt.feature_movies.di.MoviesScope
import javax.inject.Inject


@MoviesScope
class MoviesViewModel @Inject constructor(
    private val getMovies: GetMovies
    //, private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _movies: MutableLiveData<List<MovieView>> = MutableLiveData()
    val movies: LiveData<List<MovieView>> = _movies

    fun loadMovies() =
        getMovies(Params(35), viewModelScope) { it.fold(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Movie>) {
        _movies.value = movies.map { MovieView(it.id, it.poster) }
    }

}
