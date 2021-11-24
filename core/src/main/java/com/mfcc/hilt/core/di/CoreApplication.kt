package com.mfcc.hilt.core.di

import android.app.Application


abstract class CoreApplication : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    abstract fun createCoreComponent(): CoreComponent

    override fun provideCoreComponent(): CoreComponent = coreComponent

    override fun onCreate() {
        super.onCreate()

        coreComponent = createCoreComponent()
        coreComponent.inject(this)
    }
}