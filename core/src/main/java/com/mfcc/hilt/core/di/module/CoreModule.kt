package com.mfcc.hilt.core.di.module

import com.mfcc.hilt.core.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton



@Module
abstract class CoreModule {

    companion object {

        @Provides
        @Singleton
        internal fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        internal fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor).build()

        @Provides
        internal fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
                .apply {
                    HttpLoggingInterceptor.Level.BODY
                    HttpLoggingInterceptor.Level.HEADERS
                }


        @Provides
        @Singleton
        fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.urlBack)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}