package com.github.harmittaa.solarguestimations.networking

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req =  chain.request()
        // TODO! get key from SP
        val url = req.url().newBuilder().addQueryParameter("APPID", "key_from_shared_preferences").build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}