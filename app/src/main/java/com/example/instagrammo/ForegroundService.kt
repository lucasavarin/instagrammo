package com.example.instagrammo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.instagrammo.activity.LoginActivity
import com.example.instagrammo.activity.MainActivity
import com.example.instagrammo.beans.response.PostsNumberResponseBean
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.shared_prefs.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForegroundService : Service() {

    companion object{
        const val CHANNEL_ID = "Foreground Service"
    }

    private var diff : Int = 0
    private var post : Int = 0
    private var FIVE_SECONDS : Long = 5000
    private val binder = LocalBinder()


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notifyIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, notifyIntent, 0)

        Handler().postDelayed(object : Runnable{
            override fun run() {
                Client.getClient.getPostsNumber().enqueue(object : Callback<PostsNumberResponseBean>{
                    override fun onFailure(call: Call<PostsNumberResponseBean>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<PostsNumberResponseBean>,
                        response: Response<PostsNumberResponseBean>
                    ) {

                        if (post != 0){
                            diff = response.body()!!.payload.toInt() - post
                        }
                        post = response.body()!!.payload.toInt()

                        if (diff!= 0){
                            prefs.newPostNumber = prefs.newPostNumber + diff
                            prefs.isPostNumberChanged = true
                        }

                        if (!prefs.isPostNumberChanged){
                            prefs.newPostNumber = 0
                        }

                        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                            .setContentTitle("Recupero Post")
                            .setContentText("Nuovi post pubblicati : ${prefs.newPostNumber}")
                            .setSmallIcon(R.mipmap.ic_splashp_round)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .build()

                        startForeground(1, notification)
                    }
                })
            Handler().postDelayed(this, FIVE_SECONDS)}
            },FIVE_SECONDS)

        return START_NOT_STICKY
    }


    inner class LocalBinder : Binder(){
        fun getService() : ForegroundService = this@ForegroundService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

//    fun  setPostNumber(){
//        prefs.newPostNumber = (prefs.newPostNumber + diff)
//    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager?.createNotificationChannel(channel)
        }
    }
}