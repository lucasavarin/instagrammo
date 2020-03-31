package com.example.instagrammo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.instagrammo.PostNumberService
import com.example.instagrammo.prefs
import com.example.instagrammo.R
import com.example.instagrammo.ui.fragment.*
import com.google.android.material.bottomnavigation.*
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.item_badge.view.*
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
       override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val profileId = intent?.extras?.get("profileId")
        if(profileId!= null){
            makeTransaction( ProfiloFragment.newInstance(profileId.toString()))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val bottomNavigationMenu : BottomNavigationView = bottom
        val bottomNavigationMenuView = bottomNavigationMenu.getChildAt(0) as BottomNavigationMenuView
        val view: View = bottomNavigationMenuView.getChildAt(0)
        val items = view as BottomNavigationItemView
        val badge: View = LayoutInflater.from(this).inflate(R.layout.item_badge, bottomNavigationMenuView, false)
        val badgeTextView = badge.notification_badge

        items.addView(badge)
        reloadBadge(badgeTextView)

        FirebaseApp.initializeApp(applicationContext)
        startServices()
        var fragment: Fragment? = null

        if (savedInstanceState == null){
            makeTransaction(HomeFragment.newInstance())
        }

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.header)
        bottom.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                    makeText(badgeTextView)
                    makeTransaction(HomeFragment.newInstance())
                    true
                }

                R.id.profilo -> {
                    makeTransaction(ProfiloFragment.newInstance())
                    true
                }

                R.id.search -> {
                    makeTransaction(SearchFragment.newInstance())
                    true
                }
                R.id.add -> {
                    makeTransaction(AddFragment.newInstance())
                    true
                }
                R.id.favorite -> {
                    makeTransaction(FavoriteFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        stopServices()
    }

    fun AppCompatActivity.makeTransaction(f: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        if (f != null) {
            transaction.replace(R.id.frame, f)
            transaction.commit()
        }
    }

    private fun makeText(textView: TextView){
        prefs.changedPost = false
        textView.visibility = View.INVISIBLE
    }

    private fun reloadBadge(textView: TextView) {

        val handler = Handler()
        val delay : Long = 5000

        handler.postDelayed(object : Runnable {
            override fun run() {
                if(prefs.changedPost){
                    textView.visibility = View.VISIBLE
                    textView.text = prefs.postNumber.toString()
                }
                handler.postDelayed(this, delay)
            }
        }, delay)
    }

    fun startServices() {
        val intent = Intent(this, PostNumberService::class.java)
        startService(intent)
    }

    fun stopServices() {
        val intent = Intent(applicationContext, PostNumberService::class.java)
        stopService(intent)
    }

}