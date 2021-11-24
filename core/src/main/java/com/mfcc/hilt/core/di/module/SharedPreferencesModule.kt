package com.mfcc.hilt.core.di.module

import android.content.Context
import android.content.SharedPreferences
import com.mfcc.hilt.shared.FeatureScope
import dagger.Module
import dagger.Provides



@Module
open class SharedPreferencesModule(val context: Context, val name: String) {

    @Provides
    @FeatureScope
    fun provideSharedPreferences(): SharedPreferences {
        return context.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}