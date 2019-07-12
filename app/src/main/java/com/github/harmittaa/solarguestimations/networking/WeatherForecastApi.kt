package com.github.harmittaa.solarguestimations.networking

import com.github.harmittaa.solarguestimations.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherForecastApi {
    //Todo: add settings to change unit type
    @GET("weather?lat=60.72&lon=23.67&units=metric")
    suspend fun getForecast(): Forecast

}