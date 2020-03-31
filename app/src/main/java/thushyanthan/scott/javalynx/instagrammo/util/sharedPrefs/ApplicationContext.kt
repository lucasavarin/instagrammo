package thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs

import android.app.Application
import thushyanthan.scott.javalynx.instagrammo.util.database.FeedReaderDbHelper

val prefs:SharedPrefs by lazy { ApplicationContext.prefs!! }
val dbHelper:FeedReaderDbHelper by lazy { ApplicationContext.dbHelper!! }

class ApplicationContext:Application() {
    companion object{
        var prefs: SharedPrefs? = null
        var dbHelper: FeedReaderDbHelper? = null
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        dbHelper = FeedReaderDbHelper(applicationContext)
        super.onCreate()
    }
}