package com.instagrammo.util.shared_prefs

import android.app.Application
import com.mst.instagrammo.database.DBHelper

val prefs: Prefs by lazy {
    AppContext.prefs!!
}

val prefsDB: DBHelper by lazy {
    AppContext.prefsDB!!
}

class AppContext : Application(){

    companion object {
        var prefs : Prefs? = null
        var prefsDB : DBHelper? = null
    }

    override fun onCreate(){
        prefs = Prefs(applicationContext)
        prefsDB = DBHelper(applicationContext)

        super.onCreate()
    }

}