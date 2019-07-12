package com.github.harmittaa.solarguestimations.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val endpoint: String = "http://api.openweathermap.org/data/2.5/"
    private val gsonConverter = GsonConverterFactory.create()
    private val authInterceptor = AuthInterceptor() //TODO: inject!
    val forecastService: WeatherForecastApi by lazy {
        Retrofit.Builder()
            .baseUrl(endpoint)
            .client(getOkHttpClient())
            .addConverterFactory(gsonConverter).build()
            .create(WeatherForecastApi::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }
}

