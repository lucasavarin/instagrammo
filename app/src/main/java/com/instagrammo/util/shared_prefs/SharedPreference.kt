package com.instagrammo.util.shared_prefs

import android.app.Application
import com.instagrammo.util.database.DataBaseHelper

val prefs: Prefs by lazy {
    SharedPreference.prefs!!
}

val prefsDataBase: DataBaseHelper by lazy {
    SharedPreference.prefsDataBase!!
}

class SharedPreference : Application(){

    companion object {
        var prefs : Prefs? = null
        var prefsDataBase : DataBaseHelper? = null
    }

    override fun onCreate(){

        prefs = Prefs(applicationContext)
        prefsDataBase = DataBaseHelper(applicationContext)
            super.onCreate()
    }

}