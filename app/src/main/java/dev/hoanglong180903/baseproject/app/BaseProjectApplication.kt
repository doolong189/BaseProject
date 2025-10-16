package dev.hoanglong180903.baseproject.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseProjectApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}