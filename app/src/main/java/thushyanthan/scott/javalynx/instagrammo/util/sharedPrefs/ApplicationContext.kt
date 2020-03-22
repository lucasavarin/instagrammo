package thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs

import android.app.Application

val prefs:SharedPrefs by lazy { ApplicationContext.prefs!! }

class ApplicationContext:Application() {
    companion object{
        var prefs: SharedPrefs? = null
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}