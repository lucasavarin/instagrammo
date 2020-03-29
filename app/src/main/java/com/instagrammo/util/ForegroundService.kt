package com.instagrammo.util
import android.app.*
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.drawable.toIcon
import com.example.login.R
import com.instagrammo.bean.buissnes.HomeWrapperPostBean
import com.instagrammo.bean.rest.response.NotificationResponseBean
import com.instagrammo.util.retrofit.ClientInterceptor
import com.instagrammo.util.shared_prefs.prefs
import com.instagrammo.view.add_fragment.AddFragment
import com.instagrammo.view.home_fragment.CircleTransform
import com.instagrammo.view.login.FragmentsActivity
import com.instagrammo.view.login.LoginActivity
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ForegroundService : Service() {

    lateinit var notification : Notification
    private var  postNumber: Int = 0
    private var  countPost: Int = 0
    val GROUP_KEY = "group.key"

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

                if(prefs.newPostNumber.toInt() != 0){
                    createCustomNotification()
                }

               // createCustomNotification()

                if(prefs.isNewPostNumber == false){
                   prefs.newPostNumber = "0"
                }


                notification  = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                        .setContentTitle("Recupero Post")
                        .setContentText("Nuovi post pubblicati: ${prefs.newPostNumber}")
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentIntent(pendingIntent)
                        .setShowWhen(false)
                        .setOnlyAlertOnce(true)
                        .setGroup(GROUP_KEY)
                        .build()
                startForeground(1000, notification)
            }

        })
    }

    private fun  createCustomNotification(){
        val singlePostNotification = arrayListOf<Notification>()

        ClientInterceptor.getUser.getFollowerPost().enqueue(object : Callback<HomeWrapperPostBean>{

            override fun onFailure(call: Call<HomeWrapperPostBean>, t: Throwable) {
                Toast.makeText(applicationContext, "C'è stato un errore nel recupero dei posts", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<HomeWrapperPostBean>,
                response: Response<HomeWrapperPostBean>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.result) {
                        val lastPosts = response.body()!!.payload.reversed().take(prefs.newPostNumber.toInt())

                        lastPosts.forEach { it ->
                            val notificationIntent = Intent(applicationContext, FragmentsActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }

                            val pendingMainIntent = PendingIntent.getActivity(applicationContext, 0, notificationIntent,0)

                            val newNotification =
                                NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                                    .setContentTitle(it.HomeProfilePostBean.name)
                                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                    .setContentText(it.title)
                                    .setContentIntent(pendingMainIntent)
                                    .setAutoCancel(true)
                                    .setGroup(GROUP_KEY)
                                    .addAction(R.drawable.ic_launcher_background, "REPLY", pendingMainIntent)
                                     getProfileImage(it.HomeProfilePostBean.picture, newNotification)
                                singlePostNotification.add(newNotification.build())
                          }

                        NotificationManagerCompat.from(applicationContext).apply {
                            singlePostNotification.forEachIndexed { i, e ->
                                notify(i, e)
                            }
                        }
                    }
                }
            }
        })
    }

    private fun getProfileImage(url: String, notification: NotificationCompat.Builder) : Unit {

        Picasso.get().load(url).transform(CircleTransform()).into(object : Target{
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
              notification.setLargeIcon(bitmap)
            }

        })

    }
}