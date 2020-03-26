package com.example.instagrammo

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onCreate() {
        super.onCreate()

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
              if (!task.isSuccessful)  {
                  Log.w(this::class.simpleName, "getIstanceId failed", task.exception)
                  return@OnCompleteListener
              }
                //Get new Istance ID Token
                val token = task.result?.token

               // FirebaseMessaging.getInstance().isAutoInitEnabled = true
            })
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(this::class.simpleName,"Refreshed token: " + token);
    }
}