package com.github.harmittaa.solarguestimations.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Forecast(
    val weather: List<Weather>,
    val clouds: Clouds,
    @SerializedName("main") val temp: TempData,
    @SerializedName("sys") val sunTimes: SunTimes
)

data class Weather(
    @SerializedName("main") val weather: String,
    val id: Int,
    val description: String
)

data class Clouds(
    @SerializedName("all") val coverage: Int
)

data class SunTimes(
    val sunset: Int, // since epoch
    val sunrise: Int // since epoch
)

data class TempData(
    val temp: Double,
    val humidity: Int
)


