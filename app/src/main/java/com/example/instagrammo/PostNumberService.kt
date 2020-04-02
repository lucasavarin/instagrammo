package com.example.instagrammo

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.instagrammo.model.HomeWrapperPostBean
import com.example.instagrammo.model.PostsNumberResponse
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.ui.activity.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostNumberService : Service() {

    lateinit var notification: Notification
    private var post: Int = 0
    private var diff: Int = 0
    private var nPost: String = ""
    private val GROUP_KEY = "group_key"
    var oldCallPostNumber: Int = 0

    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
        var changed: Boolean = false
        var number: Int = 0
    }


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        createNotification()

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        getPostsNumber(pendingIntent)
        return START_NOT_STICKY
    }


    private fun createNotification() {
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
        handler.post(object : Runnable {
            override fun run() {
            //    getNPost(pendingIntent)
            }
        })
        val delay: Long = 5000
        handler.postDelayed(object : Runnable {
            override fun run() {
                getNumberPost(pendingIntent)
                handler.postDelayed(this, delay)
            }
        }, delay)
    }

    private fun getNPost(pendingIntent: PendingIntent) {
        RetrofitController.getClient.getNPosts().enqueue(object : Callback<PostsNumberResponse> {
            override fun onFailure(call: Call<PostsNumberResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<PostsNumberResponse>,
                response: Response<PostsNumberResponse>
            ) {
                if (post != 0) {
                    diff = response.body()!!.payload.toInt() - post
                }
                post = response.body()!!.payload.toInt()

                if (diff != 0) {
                    prefs.postNumber = prefs.postNumber + diff
                    prefs.changedPost = true
                }

                if (!prefs.changedPost) {
                    prefs.postNumber = 0
                }


                notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                    .setContentTitle("Titolo")
                    .setContentText("Nuovi post: ${prefs.postNumber}") //
                    .setSmallIcon(R.mipmap.favicon)
                    .setContentIntent(pendingIntent)
                    .setShowWhen(false)
                    .setOnlyAlertOnce(true)
                    .setGroup(GROUP_KEY)
                    .build()
                startForeground(1000, notification)
            }
        })
    }

    private fun getNumberPost(pendingIntent: PendingIntent): Unit {


        RetrofitController.getClient.getNPosts().enqueue(object :
            Callback<PostsNumberResponse> {
            override fun onFailure(call: Call<PostsNumberResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<PostsNumberResponse>,
                response: Response<PostsNumberResponse>
            ) {

                if (nPost != "" && response.isSuccessful && response.body() != null) {
                    post = response!!.body()!!.payload.toInt()

                }

                nPost = response.body()!!.payload

                if (oldCallPostNumber < post && oldCallPostNumber > 0) {
                    prepareNotifications(post - oldCallPostNumber)
                }
                oldCallPostNumber = post

                notification =
                    NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                        .setContentTitle("Titolo")
                        .setContentText("Nuovi post: $post")
                        .setSmallIcon(R.mipmap.favicon)
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setShowWhen(false)
                        .setOnlyAlertOnce(true)
                        .build()
                startForeground(1, notification)
            }
        })
    }

    private fun prepareNotifications(number: Int) {
        val notifications = arrayListOf<Notification>()
        val retrofit = RetrofitController.getClient
        retrofit.getFollowerPost().enqueue(object : Callback<HomeWrapperPostBean> {
            override fun onFailure(call: Call<HomeWrapperPostBean>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<HomeWrapperPostBean>,
                response: Response<HomeWrapperPostBean>
            ) {
                if (response.isSuccessful) {


                    val ultimiPosts = response.body()!!.payload.reversed().take(number)
                    ultimiPosts.forEach { it ->
                        val notificationIntent =
                            Intent(applicationContext, MainActivity::class.java)
                        notificationIntent.putExtra("profileId", it.profileId)
                        val pi = PendingIntent.getActivity(
                            applicationContext, 0, notificationIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                        )

                        val newMessageNotification =
                            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                                .setContentTitle("Nuovo post di ${it.profile.name}")
                                .setSmallIcon(R.mipmap.logo)
                                .setContentText(it.title)
                                .setGroup(GROUP_KEY)
                                .addAction(R.drawable.ic_launcher_background, "Profilo", pi)
                                .build()
                        notifications.add(newMessageNotification)


                    }
                    val summaryNotification =
                        NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                            .setContentText("${notifications.size} new posts")
                            .setSmallIcon(R.drawable.ic_arrow_forward_black_24dp)
                            .setStyle(NotificationCompat.InboxStyle())
                            .setGroup(GROUP_KEY)
                            .setGroupSummary(true)
                            .build()
                    NotificationManagerCompat.from(applicationContext).apply {
                        notifications.forEachIndexed { i, e ->
                            notify(i, e)
                        }
                        //  notify(0, summaryNotification)
                    }
                }
            }
        })
    }

}
