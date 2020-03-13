package com.mst.instagrammo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mst.instagrammo.R
import com.mst.instagrammo.activities.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set home as default fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, HomeFragment())
        transaction.commit()

        bottom_navbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> { showFragment(HomeFragment()) }
                R.id.nav_follow -> { showFragment(FollowFragment()) }
                R.id.nav_add -> { showFragment(AddFragment()) }
                R.id.nav_search -> { showFragment(SearchFragment()) }
                R.id.nav_profile -> { showFragment(ProfileFragment()) }
            }
            true
        }
    }

    fun showFragment(selectedFragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, selectedFragment)
        transaction.commit()
    }
}

