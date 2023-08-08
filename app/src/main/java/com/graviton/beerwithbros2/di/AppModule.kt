package com.graviton.beerwithbros2.di

import com.graviton.beerwithbros2.api.BwbApi
import com.graviton.beerwithbros2.api.BwbApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

            .client(okHttpClient)
            .build()

    @get:Singleton
    @get:Provides
    val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .build()

    @Provides
    @Singleton
    fun provideBwbApi(retrofit: Retrofit): BwbApi = retrofit.create(BwbApi::class.java)

}