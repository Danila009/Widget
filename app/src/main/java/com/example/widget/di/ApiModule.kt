package com.example.widget.di

import com.example.widget.api.MusicApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @[Provides Singleton]
    fun providerMusicRetrofit():MusicApi = Retrofit.Builder()
        .baseUrl("https://api.cfif31.ru")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MusicApi::class.java)
}