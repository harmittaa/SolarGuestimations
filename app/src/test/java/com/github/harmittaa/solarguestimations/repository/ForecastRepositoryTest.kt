package com.github.harmittaa.solarguestimations.repository

import com.github.harmittaa.solarguestimations.model.*
import com.github.harmittaa.solarguestimations.networking.WeatherForecastApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ForecastRepositoryTest {
    private val apiMock: WeatherForecastApi = mock(WeatherForecastApi::class.java)
    private val expectedForecast =
        Forecast(listOf(Weather("cloudy", 123, "clouds")), Clouds(10), TempData(10.0, 1), SunTimes(12, 24))

    @Test
    fun getForecast() {
        val forecastRepo = ForecastRepository(apiMock)
        runBlocking {
            `when`(apiMock.getForecast()).thenReturn(expectedForecast)
            val actualForecast = forecastRepo.getForecast()
            assertSame(expectedForecast, actualForecast)
        }
    }
}