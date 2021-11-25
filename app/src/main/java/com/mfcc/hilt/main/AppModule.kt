package com.mfcc.hilt.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.mfcc.hilt.core.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun provideAppContext(app: MFAPP) : Context


}