package com.example.widget.di

import dagger.Component

@Component(
    modules = [
        ApiModule::class
    ]
)
interface AppComponent