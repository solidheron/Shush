package com.example.aiattempt3.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveJsonMatchStatus(matches: Boolean) {
        prefs.edit().putBoolean("json_match_status", matches).apply()
    }

    fun getJsonMatchStatus(): Boolean {
        return prefs.getBoolean("json_match_status", false)
    }
} 