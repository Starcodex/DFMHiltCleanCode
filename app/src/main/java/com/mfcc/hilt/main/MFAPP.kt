package com.mfcc.hilt.main

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MFAPP : MultiDexApplication(), HasAndroidInjector, AppComponentProvider {

    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }

    override fun provideAppComponent() = appComponent

}