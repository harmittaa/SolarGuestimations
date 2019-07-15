package com.github.harmittaa.solarguestimations.feature.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.harmittaa.solarguestimations.R
import com.github.harmittaa.solarguestimations.persistence.ApiKeyPreferences
import com.github.harmittaa.solarguestimations.persistence.Keys
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import org.koin.dsl.module

val mainViewModelModule = module {
    factory { MainActivityViewModel() }
}

class MainActivityViewModel : ViewModel() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance() // todo! dagger
    val landingState = MutableLiveData<LandingState>()
    val spinnerVisibility = MutableLiveData<Int>()

    init {
        spinnerVisibility.value = View.VISIBLE
    }

    fun onCreate(prefs: ApiKeyPreferences) {
        remoteConfig.setDefaults(R.xml.remote_config_defaults)
        if (!prefs.hasApiKey()) {
            getApiKey(prefs)
        } else {
            spinnerVisibility.value = View.INVISIBLE
        }
    }

    private fun getApiKey(prefs: ApiKeyPreferences) {
        remoteConfig.fetch().addOnCompleteListener { it ->
            spinnerVisibility.value = View.INVISIBLE
            if (it.isSuccessful) {
                remoteConfig.activate().addOnCompleteListener { activateTask ->
                    if (activateTask.isSuccessful) {
                        prefs.storeApiKey(remoteConfig.getString(Keys.WEATHER))
                        landingState.postValue(LandingState.SUCCESS)
                    } else {
                        landingState.postValue(LandingState.ERROR)
                    }
                }
            } else {
                landingState.postValue(LandingState.ERROR)
            }
        }
    }
}

