package com.example.login

import android.app.Application

val prefs: Prefs by lazy {
    SharedPreference.prefs!!
}

class SharedPreference : Application(){

    companion object {
        var prefs : Prefs? = null
    }

    override fun onCreate(){

        prefs = Prefs(applicationContext)

            super.onCreate()
    }

}