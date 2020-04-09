package com.example.instagrammo.applicationUtils

import android.app.Application
import com.example.instagrammo.database.DatabaseHelper

val prefs:Prefs by lazy {
    ApplicationContext.prefs!!
}

val db:DatabaseHelper by lazy{
    ApplicationContext.db!!
}

class ApplicationContext: Application() {

    companion object {
        var prefs: Prefs? = null
        var db: DatabaseHelper? = null
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        db = DatabaseHelper(applicationContext)
        super.onCreate()
    }
}