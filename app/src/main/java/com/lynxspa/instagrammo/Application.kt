package com.lynxspa.instagrammo

import android.app.Application
import com.lynxspa.instagrammo.db.DbHelper




class Application : Application() {

    companion object{

        var db : DbHelper? = null
    }


    override fun onCreate() {
        super.onCreate()
        db = DbHelper(applicationContext)
    }

}