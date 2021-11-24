package com.mfcc.hilt.core.di

import android.content.Context
import com.mfcc.hilt.core.base.BaseActivity
import com.mfcc.hilt.core.base.BaseFragment
import dagger.BindsInstance
import dagger.Component


/*
@Component(dependencies = [ActivityViewModelModule::class])
interface ActivityViewModelComponent {

    fun inject(activity: BaseActivity)
    fun inject(fragment: BaseFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(activityViewModelModule: ActivityViewModelModule): Builder
        fun build(): ActivityViewModelComponent
    }
}
*/