package com.mfcc.hilt.feature_movies.list



import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.mfcc.hilt.core.base.BaseViewModel
import com.mfcc.hilt.core.common.Resource
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
        getMovies(Params("upcoming"), viewModelScope) { it.fold(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Movie>) {
        _movies.value = movies.map { MovieView(it.id, it.poster) }
    }



    fun loadMoviesLD() = handleLiveData(
        getMovies.exec(Params("upcoming")),
        { handleMovieList(it.data!!) },
        {  handleError(it) }
    )


    /*
    val liveData = MediatorLiveData<List<Movie>>()

    fun loadMovies() =
        liveData.addSource(getMovies.exec(Params("upcoming")), Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {  }
                Resource.Status.ERROR -> { }
                Resource.Status.LOADING -> {}
            }
    })
    */

}
