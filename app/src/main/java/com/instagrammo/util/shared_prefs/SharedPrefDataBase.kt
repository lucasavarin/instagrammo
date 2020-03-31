package com.instagrammo.util.shared_prefs

import android.app.Application
import com.instagrammo.util.database.DataBaseHelper

val prefsDataBase: DataBaseHelper by lazy {
    SharedPrefDataBase.prefsDataBase!!
}

class SharedPrefDataBase : Application(){


    companion object {
        var prefsDataBase : DataBaseHelper? = null
    }

    override fun onCreate(){

        prefsDataBase = DataBaseHelper(applicationContext)

        super.onCreate()
    }

}