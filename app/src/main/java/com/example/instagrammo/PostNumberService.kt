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
import retrofit2.Response
import retrofit2.Callback


class PostNumberService : Service() {

    lateinit var notification : Notification
    private var post: Int = 0
    private var nPost: String = ""
    private val GROUP_KEY =  "group_key"


    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        createNotification()
     /*   val intent = PendingIntent.getActivity(
            applicationContext, 0, Intent(applicationContext, MainActivity::class.java), 0
        )
      */
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        getPostsNumber(pendingIntent)
        notification  =
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
        handler.post(object : Runnable{
            override fun run() {
            }
        })
        val delay : Long = 5000
        handler.postDelayed(object : Runnable {
            override fun run() {
                getNumberPost(pendingIntent)
                handler.postDelayed(this, delay)
            }
        }, delay)
    }

    private fun getNumberPost(pendingIntent: PendingIntent) : Unit {
        var oldCallPostNumber:Int=0

        RetrofitController.getClient.getNPosts().enqueue(object :
            Callback<PostsNumberResponse> {
            override fun onFailure(call: Call<PostsNumberResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<PostsNumberResponse>,
                response: Response<PostsNumberResponse>
            ) {

                if(nPost != "" && response.isSuccessful && response.body()!= null){
                    post =  response!!.body()!!.payload.toInt()  - nPost.toInt()

                }

                nPost = response.body()!!.payload
                if(oldCallPostNumber <post){
                    prepareNotifications()
                }
                oldCallPostNumber = post

                notification  =
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

   private fun  prepareNotifications(){
       val notifications  = arrayListOf<Notification>()
       val retrofit = RetrofitController.getClient
       retrofit.getFollowerPost().enqueue(object : Callback<HomeWrapperPostBean>{
           override fun onFailure(call: Call<HomeWrapperPostBean>, t: Throwable) {

           }

           override fun onResponse(
               call: Call<HomeWrapperPostBean>,
               response: Response<HomeWrapperPostBean>
           ) {
               if(response.isSuccessful){


                   val ultimiPosts =   response.body()!!.payload.reversed().take(post)
                    ultimiPosts.forEach{it->
                        val notificationIntent = Intent(applicationContext, MainActivity::class.java)
                        notificationIntent.putExtra("profileId",it.profileId)
                        val pi = PendingIntent.getActivity(applicationContext, 0, notificationIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT)

                       val newMessageNotification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                           .setContentTitle("Nuovo post di ${it.profile.name}")
                           .setSmallIcon(R.mipmap.logo)
                           .setContentText(it.title)
                           .setGroup(GROUP_KEY)
                           .addAction(R.drawable.ic_launcher_background,"Profilo",pi)
                           .build()
                       notifications.add(newMessageNotification)


                   }
                   val summaryNotification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                       .setContentText("${notifications.size} new posts")
                       .setSmallIcon(R.drawable.ic_arrow_forward_black_24dp)
                       .setStyle(NotificationCompat.InboxStyle())
                       .setGroup(GROUP_KEY)
                       .setGroupSummary(true)
                       .build()
                   NotificationManagerCompat.from(applicationContext).apply {
                      notifications.forEachIndexed{ i,e->
                          notify(i,e)
                      }
                     //  notify(0, summaryNotification)
                   }
               }
           }
       })
   }

}
