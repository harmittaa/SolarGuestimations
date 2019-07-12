package com.github.harmittaa.solarguestimations.persistence

import android.annotation.SuppressLint
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { ApiKeyPreferences(androidContext()) }
}

class ApiKeyPreferences constructor(val ctx: Context) {
    private val prefKey = "ApiKeyPreferences"
    private val editor = ctx.getSharedPreferences(prefKey, Context.MODE_PRIVATE)

    @SuppressLint("ApplySharedPref")
    fun storeApiKey(key: String) {
        editor.edit().putString(Keys.WEATHER, key).commit()
    }

    fun hasApiKey(): Boolean {
        return editor.contains(prefKey)
    }

    fun getApiKey(): String {
        return editor.getString(Keys.WEATHER, "key")!!
    }
}