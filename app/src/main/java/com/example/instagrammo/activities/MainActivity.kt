package com.example.instagrammo.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import com.example.instagrammo.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import com.example.instagrammo.util.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(
            HomeFragment.makeInstance(),
            R.id.container
        )

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.home -> replaceFragment(
                    HomeFragment.makeInstance(),
                    R.id.container
                )
                R.id.search -> replaceFragment(
                    SearchFragment.makeInstance(),
                    R.id.container
                )
                R.id.add -> replaceFragment(
                    AddFragment.makeInstance(),
                    R.id.container
                )
                R.id.follow -> replaceFragment(
                    FavoritesFragment.makeInstance(),
                    R.id.container
                )
                R.id.profile -> replaceFragment(
                    ProfileFragment.makeInstance(),
                    R.id.container
                )
                else -> false
            }
        }
    }
}