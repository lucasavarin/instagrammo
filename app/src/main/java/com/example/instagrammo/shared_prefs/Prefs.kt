package com.example.instagrammo.shared_prefs

import android.content.Context
import android.content.SharedPreferences

class Prefs(context:Context) {
    private val PREFS_FILENAME = "com.example.instagrammo.shared_prefs.prefs"
    private val REMEMBER_USER = "remember_user"
    private val prefs:SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var remember_user:Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()
}