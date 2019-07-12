package com.github.harmittaa.solarguestimations.feature.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.harmittaa.solarguestimations.model.Forecast
import com.github.harmittaa.solarguestimations.repository.ForecastRepository

class ForecastViewModel : ViewModel() {
    private val forecastRepository = ForecastRepository()

    val forecast: LiveData<Forecast> = liveData {
        emit(forecastRepository.getForecast())
    }
}