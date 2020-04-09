package com.example.instagrammo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token : $token")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "com.lynxspa.instagrammo"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID)
            channel.canBypassDnd()
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)

        notificationBuilder.setAutoCancel(true)
            .setContentTitle("instagrammo")
            .setContentText(p0.notification?.body)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_splashp_round)

        notificationManager.notify(1000, notificationBuilder.build())
    }
}