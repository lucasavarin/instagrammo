package com.example.instagrammo

import android.app.Application
import android.database.sqlite.SQLiteDatabase
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
        prefs = Prefs(applicationContext)
        db = AppDbHelper(applicationContext)

        super.onCreate()
    }
}