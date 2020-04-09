package com.example.instagrammo.util

import android.app.Application
import com.example.instagrammo.db.AppDbHelper

val prefs: Prefs by lazy {
    ApplicationContext.prefs!!
}

val db  : AppDbHelper by lazy {
    ApplicationContext.db!!
}
class ApplicationContext : Application() {
    companion object{
        var prefs: Prefs? = null
        var db : AppDbHelper? = null

    }

    override fun onCreate() {
        prefs =
            Prefs(applicationContext)
        db = AppDbHelper(applicationContext)

        super.onCreate()
    }
}