package com.example.instagrammo.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.ForegroundService
import com.example.instagrammo.R
import com.example.instagrammo.primary_fragments.*
import com.example.instagrammo.retrofit.Session
import com.example.instagrammo.shared_prefs.prefs
import com.example.instagrammo.util.addFragment
import com.example.instagrammo.util.replaceFragment
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(applicationContext)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_toolbar)

        addFragment(HomeFragment.makeInstance(), R.id.container)
        initService()

        navView.setOnNavigationItemSelectedListener { menuItem : MenuItem ->
            when(menuItem.itemId){
                R.id.home ->{
                    prefs.isPostNumberChanged = false
                    prefs.newPostNumber = 0
                    replaceFragment(HomeFragment.makeInstance(),
                        R.id.container
                    )
                }
                R.id.search ->{
                    replaceFragment(SearchFragment.makeInstance(),
                        R.id.container
                    )
                }
                R.id.user ->{
                    replaceFragment(UserFragment.makeInstance(Session.profileId),
                        R.id.container
                    )
                }
                R.id.add ->{
                    replaceFragment(AddFragment.makeInstance(),
                        R.id.container
                    )
                }
                R.id.favorite ->{
                    replaceFragment(FavoriteFragment.makeInstance(),
                        R.id.container
                    )
                }
                else -> false
            }
        }
            calcPostNumber()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val profileId = intent?.getStringExtra("profileId")
        if (profileId!=null){
            replaceFragment(UserFragment.makeInstance(profileId), R.id.container)
        }
    }

    private fun initService(){
        val postNumberService = Intent(applicationContext, ForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(postNumberService)
        }else{
            startService(postNumberService)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        stopServices()
    }

    private fun calcPostNumber(){
        Handler().postDelayed(object : Runnable{
            override fun run() {
                if (prefs.newPostNumber != 0) {
                    navView.getOrCreateBadge(R.id.home).number = prefs.newPostNumber
                }else{
                    navView.removeBadge(R.id.home)
                }
            Handler().postDelayed(this,5000)}
        }, 5000)
    }

    private fun stopServices(){
        val stopServiceIntent = Intent(applicationContext, ForegroundService::class.java)
        stopService(stopServiceIntent)
    }
}