package com.example.util.shared_prefs

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val PREF_NAME = "com.example.util.shared_prefs.prefs"
    private val REMEMBER_USER ="REMEMBER_USER"
    val USER = "user"
    val PASSWORD = "pass"
    val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0);

    var user: String
        get() = prefs.getString(USER, "") ?: ""
        set(value) = prefs.edit().putString(USER, value).apply()

    var password: String
        get() = prefs.getString(PASSWORD, "") ?: ""
        set(value) = prefs.edit().putString(PASSWORD, value).apply()

    var rememberMe: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()
}
