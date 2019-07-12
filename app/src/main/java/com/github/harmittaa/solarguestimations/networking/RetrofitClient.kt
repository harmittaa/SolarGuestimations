package com.github.harmittaa.solarguestimations.networking

import com.github.harmittaa.solarguestimations.BuildConfig
import com.github.harmittaa.solarguestimations.persistence.ApiKeyPreferences
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideAuthInterceptor(get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideForecastApi(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideAuthInterceptor(prefs: ApiKeyPreferences): AuthInterceptor = AuthInterceptor(prefs)

fun provideForecastApi(retrofit: Retrofit): WeatherForecastApi = retrofit.create(WeatherForecastApi::class.java)
