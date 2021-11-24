package com.mfcc.hilt.feature_movies

import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLink
import com.mfcc.hilt.core.base.BaseBindingFragmentActivity
import com.mfcc.hilt.core.databinding.ActivityContainerBinding
import com.mfcc.hilt.feature_movies.di.DaggerMoviesComponent
import com.mfcc.hilt.feature_movies.list.MoviesFragment
import com.mfcc.hilt.main.InjectUtils


@DeepLink("mfcc://movies")
class MoviesListActivity : BaseBindingFragmentActivity<ActivityContainerBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMoviesComponent.builder()
            .appComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build().inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun fragment() = MoviesFragment()
}