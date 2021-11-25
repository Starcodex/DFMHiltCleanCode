package com.mfcc.hilt.feature_movies.list

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mfcc.hilt.core.base.BaseInjectableFragment
import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Failure.*
import com.mfcc.hilt.core.extensions.*
import com.mfcc.hilt.feature_movies.R
import com.mfcc.hilt.feature_movies.databinding.FragmentMoviesListBinding
import com.mfcc.hilt.feature_movies.di.DaggerMoviesComponent
import com.mfcc.hilt.feature_movies.failure.MovieFailure.*
import com.mfcc.hilt.main.InjectUtils
import javax.inject.Inject


class MoviesFragment : BaseInjectableFragment<MoviesViewModel, FragmentMoviesListBinding>() {

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override val layoutResource: Int
        get() = R.layout.fragment_movies_list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            observe(movies, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMoviesList()
    }


    private fun initializeView() {
        binding.movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.movieList.adapter = moviesAdapter

        /*
        moviesAdapter.clickListener = { movie, navigationExtras ->
            //navigator.showMovieDetails(requireActivity(), movie, navigationExtras)
        }
        */
    }



    private fun loadMoviesList() {
        binding.emptyView.invisible()
        binding.movieList.visible()
        showProgress()
        viewModel.loadMovies()
    }

    private fun renderMoviesList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
            else -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        binding.movieList.invisible()
        binding.emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
    }



    override fun injection() {
        DaggerMoviesComponent.factory()
            .create(InjectUtils.provideBaseComponent(appContext))
            .inject(this)
    }
}