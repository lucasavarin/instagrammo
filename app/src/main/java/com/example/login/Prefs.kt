package com.example.login

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val PREF_NAME = "com.example.res.values.prefs"
    private val REMEMBER_USER ="REMEMBER_USER"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)

    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

}
