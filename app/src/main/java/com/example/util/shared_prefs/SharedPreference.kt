package com.example.util.shared_prefs

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