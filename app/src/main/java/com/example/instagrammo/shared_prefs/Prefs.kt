package com.example.instagrammo.shared_prefs

import android.content.Context
import android.content.SharedPreferences

class Prefs(context:Context) {
    private val PREFS_FILENAME = "com.example.instagrammo.shared_prefs.prefs"
    val USER = "user"
    val PASSWORD = "password"
    private val REMEMBER_USER = "remember_user"
    private val POST_NUMBER = "POST_NUMBER"
    private val IS_POST_NUMBER_CHANGED = "IS_POST_NUMBER_CHANGED"
    private val prefs:SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var remember_user:Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

    var user : String
        get() = prefs.getString(USER, "") ?: ""
        set(value) = prefs.edit().putString(USER, value).apply()

    var password : String
        get() = prefs.getString(PASSWORD, "") ?: ""
        set(value) = prefs.edit().putString(PASSWORD, value).apply()

    var newPostNumber: Int
        get() = prefs.getInt(POST_NUMBER, 0)
        set(value) = prefs.edit().putInt(POST_NUMBER, value).apply()

    var isPostNumberChanged : Boolean
        get() = prefs.getBoolean(IS_POST_NUMBER_CHANGED, false)
        set(value) = prefs.edit().putBoolean(IS_POST_NUMBER_CHANGED, value).apply()
}