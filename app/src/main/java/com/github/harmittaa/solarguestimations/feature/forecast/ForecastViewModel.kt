package com.github.harmittaa.solarguestimations.feature.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.harmittaa.solarguestimations.model.Forecast
import com.github.harmittaa.solarguestimations.repository.ForecastRepository
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.inject

class ForecastViewModel(private val forecastRepository: ForecastRepository) : ViewModel() {

    val forecast: LiveData<Forecast> = liveData {
        emit(forecastRepository.getForecast())
    }
}