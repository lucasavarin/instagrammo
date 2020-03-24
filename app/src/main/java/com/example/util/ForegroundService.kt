package com.example.util

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import com.example.bean.rest.response.NotificationResponseBean
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import com.example.view.login.FragmentsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForegroundService : Service() {

    lateinit var notification : Notification
    private var  postNumber: String = ""
    private var  newPost: Int = 0

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        createNotificationChannel()
        val notificationIntent = Intent(this, FragmentsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        getPostsNumber(pendingIntent)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()


    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    private fun getPostsNumber(pendingIntent: PendingIntent) {

        val handler = Handler()
        val delay : Long = 5000

        handler.postDelayed(object : Runnable {
            override fun run() {

                getNumberPost(pendingIntent)

                handler.postDelayed(this, delay)

            }
        }, delay)

    }

    private fun getNumberPost(pendingIntent: PendingIntent) : Unit {

        ClientInterceptor.getUser.getPostNumber().enqueue(object :
            Callback<NotificationResponseBean> {
            override fun onFailure(call: Call<NotificationResponseBean>, t: Throwable) {
            }


            override fun onResponse(
                call: Call<NotificationResponseBean>,
                response: Response<NotificationResponseBean>
            ) {

                if(postNumber != ""){
                    newPost =  response.body()!!.payload.toInt()  - postNumber.toInt()
                }

                postNumber = response.body()!!.payload

                notification  =
                    NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                        .setContentTitle("Recupero Post")
                        .setContentText("Nuovi post pubblicati: $newPost")
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentIntent(pendingIntent)
                        .setShowWhen(false)
                        .setOnlyAlertOnce(true)
                        .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                        .build()
                startForeground(1, notification)
            }

        })
    }
}