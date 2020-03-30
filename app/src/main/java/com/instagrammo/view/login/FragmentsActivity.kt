package com.instagrammo.view.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.login.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.instagrammo.util.ForegroundService
import com.instagrammo.util.shared_prefs.prefs
import com.instagrammo.view.add_fragment.AddFragment
import com.instagrammo.view.favourite_fragment.FavouriteFragment
import com.instagrammo.view.home_fragment.HomeFragment
import com.instagrammo.view.profile_fragment.ProfileFragment
import com.instagrammo.view.search_fragment.SearchFragment


class FragmentsActivity : AppCompatActivity(){

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val action = intent!!.action

        if(action.equals("add")){
            loadFragment(AddFragment())
        }
        else{
            val profileId = intent.extras?.get("profileId")
            if(profileId != null){
                loadFragment(ProfileFragment.newInstance(profileId.toString()))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        FirebaseApp.initializeApp(applicationContext)
        startServices()

        val bottomNavigationMenu : BottomNavigationView = findViewById(R.id.nav_view)
        val bottomNavigationMenuView = bottomNavigationMenu.getChildAt(0) as BottomNavigationMenuView

        val v: View = bottomNavigationMenuView.getChildAt(0)
        val items = v as BottomNavigationItemView

        val badge: View = LayoutInflater.from(this).inflate(R.layout.badge_layout, bottomNavigationMenuView, false)
        val badgeTextView = badge.findViewById<TextView>(R.id.notification_badge)

        items.addView(badge)
        reloadBadgeContent(badgeTextView)

        if(savedInstanceState == null)
            loadFragment(HomeFragment())

        bottomNavigationMenu.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.home -> {
                    makeTextViewPost(badgeTextView)
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.Profile -> {
                    loadFragment(ProfileFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.Add -> {
                    loadFragment(AddFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.favourite -> {
                    loadFragment(FavouriteFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.search -> {
                    loadFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true

                }

                else  -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopServices()
    }

    private fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }

    private fun makeTextViewPost(textView: TextView){
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
        val serviceIntent = Intent(applicationContext, ForegroundService::class.java)
        stopService(serviceIntent)
    }
    private fun startServices() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        startService(serviceIntent)
    }

}