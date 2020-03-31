package com.example.instagrammo.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import com.example.instagrammo.applicationUtils.prefs
import com.example.instagrammo.fragments.*
import com.example.instagrammo.fragments.ProfileFragment.Companion.makeInstance
import com.example.instagrammo.services.ForegroundNotificationService
import kotlinx.android.synthetic.main.activity_main.*
import com.example.instagrammo.util.*
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.badge_layout_item.*


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startServices()

        setContentView(R.layout.activity_main)

        val bottomNavigationMenuView = bottom_navigation.getChildAt(0) as BottomNavigationMenuView
        val homeItemView = bottomNavigationMenuView.getChildAt(0) as BottomNavigationItemView

        val badge = LayoutInflater.from(this).inflate(R.layout.badge_layout_item, homeItemView, false)
        homeItemView.addView(badge)

        reloadBadgeContent(textView)

        addFragment(
            HomeFragment.makeInstance(),
            R.id.container,
            "home"
        )

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    createPostNumberBadge()
                    replaceFragment(
                        HomeFragment.makeInstance(),
                        R.id.container,
                        "home"
                    )
                    true
                }
                R.id.search -> {
                    replaceFragment(
                        SearchFragment.makeInstance(),
                        R.id.container,
                        "search"
                    )
                    true
                }
                R.id.add -> {
                    replaceFragment(
                        AddFragment.makeInstance(),
                        R.id.container,
                        "add"
                    )
                    true
                }
                R.id.follow -> {
                    replaceFragment(
                        FavoritesFragment.makeInstance(),
                        R.id.container,
                        "follow"
                    )
                    true
                }
                R.id.profile -> {
                    replaceFragment(
                        ProfileFragment.makeInstance(),
                        R.id.container,
                        "profile"
                    )
                    true
                }
                else -> false
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if(getFragmentByTag("home") != null){
            finishAffinity()
        } else {
            replaceFragment(
                HomeFragment.makeInstance(),
                R.id.container,
                "home"
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopServices()
    }

    private fun createPostNumberBadge(){
        prefs.isNewPostNumber = false
        textView.visibility = View.INVISIBLE
    }

    private fun reloadBadgeContent(textView: TextView) {

        val handler = Handler()
        val delay : Long = 5000

        handler.postDelayed(object : Runnable {
            override fun run() {

                if(prefs.isNewPostNumber){
                    textView.visibility = View.VISIBLE
                    textView.text = prefs.newPostNumber
                }

                handler.postDelayed(this, delay)

            }
        }, delay)

    }

    private fun stopServices() {
        val serviceIntent = Intent(applicationContext, ForegroundNotificationService::class.java)
        stopService(serviceIntent)
    }
    private fun startServices() {
        val serviceIntent = Intent(this, ForegroundNotificationService::class.java)
        startService(serviceIntent)
    }
}
