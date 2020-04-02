package com.instagrammo.util.shared_prefs

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val PREF_NAME = "com.instagrammo.util.shared_prefs"
    private val REMEMBER_USER ="REMEMBER_USER"

    val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)

    var rememberMe: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()
}