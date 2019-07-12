package com.github.harmittaa.solarguestimations.repository

import com.github.harmittaa.solarguestimations.networking.WeatherForecastApi
import org.koin.dsl.module

val forecastModule = module {
    single { provideForecastRepository(get()) }
}

fun provideForecastRepository(weatherForecastApi: WeatherForecastApi) = ForecastRepository(weatherForecastApi)

class ForecastRepository(private val weatherForecastApi: WeatherForecastApi) {
    suspend fun getForecast() = weatherForecastApi.getForecast()
}