package com.mfcc.hilt.main

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class
])
interface AppComponent : AndroidInjector<MFAPP> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: MFAPP): AppComponent
    }
}