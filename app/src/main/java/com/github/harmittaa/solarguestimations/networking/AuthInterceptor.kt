package com.github.harmittaa.solarguestimations.networking

import com.github.harmittaa.solarguestimations.persistence.ApiKeyPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val prefs: ApiKeyPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url().newBuilder().addQueryParameter("APPID", prefs.getApiKey()).build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}