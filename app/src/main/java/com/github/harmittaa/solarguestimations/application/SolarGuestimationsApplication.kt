package com.github.harmittaa.solarguestimations.application

import android.app.Application
import com.github.harmittaa.solarguestimations.feature.main.MainActivityViewModel
import com.github.harmittaa.solarguestimations.networking.networkModule
import com.github.harmittaa.solarguestimations.persistence.ApiKeyPreferences
import com.github.harmittaa.solarguestimations.persistence.prefModule
import com.github.harmittaa.solarguestimations.repository.forecastModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class SolarGuestimationsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@SolarGuestimationsApplication)
            modules(forecastModule, networkModule, prefModule)
        }
    }
}