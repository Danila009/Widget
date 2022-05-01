package com.example.widget

import android.app.Application
import com.example.widget.di.DaggerAppComponent

class AppApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create()
    }
}