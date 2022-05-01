package com.example.widget.di

import com.example.widget.api.repository.ApiRepository
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class
    ]
)
@Singleton
interface AppComponent{

    fun apiRepository():ApiRepository
}