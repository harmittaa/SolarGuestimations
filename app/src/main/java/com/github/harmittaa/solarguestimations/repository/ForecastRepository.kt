package com.github.harmittaa.solarguestimations.repository

import com.github.harmittaa.solarguestimations.networking.RetrofitClient
import com.github.harmittaa.solarguestimations.networking.WeatherForecastApi
import java.text.SimpleDateFormat
import java.util.*

class ForecastRepository {
    private val client: WeatherForecastApi = RetrofitClient().forecastService

    suspend fun getForecast() = client.getForecast()

    private fun getDateWeekFromNow(): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, 7)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(Date())
    }
}