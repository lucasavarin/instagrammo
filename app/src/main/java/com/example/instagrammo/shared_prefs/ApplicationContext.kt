package com.example.instagrammo.shared_prefs

import android.app.Application

val prefs : Prefs by lazy {
    ApplicationContext.prefs!!
}

class ApplicationContext: Application() {
    companion object{
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}