package com.example.util.shared_prefs

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val PREF_NAME = "com.example.util.shared_prefs.prefs"
    private val REMEMBER_USER ="REMEMBER_USER"
    private val POST_NUMBER = "POST_NUMBER"
    private val IS_NEW_POST_NUMBER = "IS_NEW_POST_NUMBER"
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

    var newPostNumber: String
        get() = prefs.getString(POST_NUMBER, "") ?: ""
        set(value) = prefs.edit().putString(POST_NUMBER, value).apply()

    var isNewPostNumber: Boolean
        get() = prefs.getBoolean(IS_NEW_POST_NUMBER, false)
        set(value) = prefs.edit().putBoolean(IS_NEW_POST_NUMBER, value).apply()
}
