package com.github.harmittaa.solarguestimations.application

import android.app.Application
import timber.log.Timber

class SolarGuestimationsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}