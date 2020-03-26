package com.example.util

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import com.example.bean.rest.response.NotificationResponseBean
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import com.example.util.shared_prefs.prefs
import com.example.view.login.FragmentsActivity
import com.example.view.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForegroundService : Service() {

    lateinit var notification : Notification
    private var  postNumber: Int = 0
    private var  countPost: Int = 0

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        createNotificationChannel()
        val notificationIntent = Intent(this, FragmentsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        getPostsNumber(pendingIntent)

        return START_NOT_STICKY
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object serviceForeground{
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

        handler.post(object : Runnable {
            override fun run() {

                getNumberPost(pendingIntent)

                handler.postDelayed(this, delay)

            }
        })

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

                if(postNumber != 0 ){
                    countPost =  response.body()!!.payload  - postNumber
                }
                    postNumber = response.body()!!.payload


                if(countPost != 0){
                    prefs.newPostNumber = Integer.toString(prefs.newPostNumber.toInt() + countPost)
                    prefs.isNewPostNumber = true
                }

                if(prefs.isNewPostNumber == false){
                   prefs.newPostNumber = "0"
                }

                notification  =
                    NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                        .setContentTitle("Recupero Post")
                        .setContentText("Nuovi post pubblicati: ${prefs.newPostNumber}")
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