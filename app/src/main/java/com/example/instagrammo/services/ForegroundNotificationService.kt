package com.example.instagrammo.services

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.instagrammo.R
import com.example.instagrammo.activities.MainActivity
import com.example.instagrammo.applicationUtils.prefs
import com.example.instagrammo.beans.response.NotificationResponseREST
import com.example.instagrammo.retrofit.RetrofitController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForegroundNotificationService: Service() {

    companion object{
        const val CHANNEL_ID = "ForegroundNotificationChannel"
    }

    lateinit var notification:Notification
    private var postNumber:Int = 0
    private var countPost:Int = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        getPostNumber(pendingIntent)

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager?.createNotificationChannel(channel)
        }
    }

    private fun getPostNumber(pendingIntent: PendingIntent){
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

        RetrofitController.getClient.getPostNumber().enqueue(object :
            Callback<NotificationResponseREST> {
            override fun onFailure(call: Call<NotificationResponseREST>, t: Throwable) {
            }


            override fun onResponse(
                call: Call<NotificationResponseREST>,
                response: Response<NotificationResponseREST>
            ) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        val body = response.body()!!
                        if(body.result){
                            if(postNumber != 0 ){
                                countPost =  response.body()!!.payload  - postNumber
                            }
                            postNumber = response.body()!!.payload


                            if(countPost != 0){
                                prefs.newPostNumber = Integer.toString(prefs.newPostNumber.toInt() + countPost)
                                prefs.isNewPostNumber = true
                            }

                            if(!prefs.isNewPostNumber){
                                prefs.newPostNumber = "0"
                            }

                            notification  =
                                NotificationCompat.Builder(applicationContext,
                                    CHANNEL_ID
                                )
                                    .setContentTitle("Recupero Post")
                                    .setContentText("Nuovi post pubblicati: ${prefs.newPostNumber}")
                                    .setSmallIcon(R.drawable.ic_notifications)
                                    .setContentIntent(pendingIntent)
                                    .setShowWhen(false)
                                    .setOnlyAlertOnce(true)
                                    .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                                    .build()
                            startForeground(1, notification)
                        }
                    }
                }
            }
        })
    }
}