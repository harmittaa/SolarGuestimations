package com.github.harmittaa.solarguestimations.persistence

import android.content.Context

class ApiKeyPreferences constructor(val ctx: Context) {
    private val prefKey = "ApiKeyPreferences"

    fun storeApiKey(key: String) {
        val editor = ctx.getSharedPreferences(prefKey, Context.MODE_PRIVATE)
        editor.edit().putString(Keys.WEATHER, key).apply()
    }

    fun hasApiKey(): Boolean {
        val editor = ctx.getSharedPreferences(prefKey, Context.MODE_PRIVATE)
        return editor.contains(prefKey)
    }

    fun getApiKey(): String {
        val editor = ctx.getSharedPreferences(prefKey, Context.MODE_PRIVATE)
        return editor.getString(prefKey, "key")!!
    }
}