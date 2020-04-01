package com.example.instagrammo.shared_prefs

import android.app.Application
import com.example.instagrammo.db.DbHelper

val prefs : Prefs by lazy {
    ApplicationContext.prefs!!
}

val db : DbHelper by lazy {
    ApplicationContext.db!!
}

class ApplicationContext: Application() {
    companion object{
        var prefs: Prefs? = null
        var db: DbHelper? = null

    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        db = DbHelper(applicationContext)
        super.onCreate()
    }
}