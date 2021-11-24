package com.mfcc.hilt.main

import android.content.Context

object InjectUtils {

    fun provideBaseComponent(applicationContext: Context): AppComponent {
        return if (applicationContext is AppComponentProvider) {
            (applicationContext as AppComponentProvider).provideAppComponent()
        } else {
            throw IllegalStateException("Provide the application context which implement BaseComponentProvider")
        }
    }

}