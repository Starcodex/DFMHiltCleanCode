package com.mfcc.hilt.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.mfcc.hilt.core.di.NetworkHandler
import com.mfcc.hilt.core.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class AppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun provideAppContext(app: MFAPP) : Context

    companion object{

        //@Provides
        //fun provideNetworkHandler(app: MFAPP) : NetworkHandler = NetworkHandler(app.applicationContext)

    }



}