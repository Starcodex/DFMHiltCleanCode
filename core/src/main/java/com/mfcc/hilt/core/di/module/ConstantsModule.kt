package com.mfcc.hilt.core.di.module

import android.os.Build
import com.kienht.gapo.shared.qualifier.ApplicationIdQualifier
import com.kienht.gapo.shared.qualifier.DebugModeQualifier
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Named
import javax.inject.Singleton


@Module
class ConstantsModule(

) {

    @Provides
    @Singleton
    @Named("BackUrl")
    internal fun provideBackUrl() : String = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    @Named("BackImgUrl")
    internal fun provideBackImgUrl() : String = "https://image.tmdb.org/t/p/w300/"

}