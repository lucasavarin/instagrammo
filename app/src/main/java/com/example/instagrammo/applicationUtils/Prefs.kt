package com.example.instagrammo.applicationUtils

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_FILENAME = "com.example.instagrammo.prefs"
    val REMEMBER = "rememberMe"
    val USERNAME = "user"
    val PASSWORD = "pass"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var rememberMe: Boolean
        get() = prefs.getBoolean(REMEMBER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER, value).apply()

    var username: String
        get() = prefs.getString(USERNAME, "") ?: ""
        set(value) = prefs.edit().putString(USERNAME, value).apply()


    var password: String
        get() = prefs.getString(PASSWORD, "") ?: ""
        set(value) = prefs.edit().putString(PASSWORD, value).apply()
}