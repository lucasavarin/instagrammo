package com.example.instagrammo

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.instagrammo.activity.LoginActivity
import com.example.instagrammo.activity.MainActivity
import com.example.instagrammo.beans.response.HomeWrapperPostBean
import com.example.instagrammo.beans.response.PostsNumberResponseBean
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.shared_prefs.prefs
import com.example.instagrammo.util.CircleTrasformation
import com.squareup.picasso.Picasso
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
    private val GROUP_KEY = "group_key"


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notifyIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, notifyIntent, 0)
        createRunningNotification(pendingIntent)

        return START_NOT_STICKY
    }


    inner class LocalBinder : Binder(){
        fun getService() : ForegroundService = this@ForegroundService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private fun createRunningNotification(pendingIntent : PendingIntent){
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

                        if (prefs.newPostNumber!= 0){
                            createCustomNotification()
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

    }

    private fun createCustomNotification(){
        Client.getClient.getPosts().enqueue(object : Callback<HomeWrapperPostBean>{
            override fun onFailure(call: Call<HomeWrapperPostBean>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<HomeWrapperPostBean>,
                response: Response<HomeWrapperPostBean>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.result) {
                        val uploadedPost =
                            response.body()!!.payload.reversed().take(prefs.newPostNumber)

                        uploadedPost.forEach {
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.putExtra("profileId", it.profileId)
                            val pendingIntent = PendingIntent.getActivity(
                                applicationContext,
                                0,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                            )
                            val builder =
                                NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                                    .setContentTitle("${it.profile.name} Ha caricato un nuovo post")
                                    .setSmallIcon(R.mipmap.ic_splashp_round)
                                    .setGroup(GROUP_KEY)
                                    .setContentText(it.title)
                                    .setAutoCancel(true)
                                    .setContentIntent(pendingIntent)
                                    .addAction(R.mipmap.ic_launcher_round, "Profilo", pendingIntent)
                            getProfileImg(it.profile.picture, builder)

                            val notification = builder.build()

                            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            notificationManager.notify(it.postId.toInt(), notification)

                        }
                    }
                }
            }
        })
    }

    private fun getProfileImg(url: String, builder: NotificationCompat.Builder){
        Picasso.get().load(url).transform(CircleTrasformation()).into(object : com.squareup.picasso.Target{
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                builder.setLargeIcon(bitmap)

            }
        })
    }

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