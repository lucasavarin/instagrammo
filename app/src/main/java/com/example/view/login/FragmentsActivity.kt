package com.example.view.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bean.rest.response.NotificationResponseBean
import com.example.login.R
import com.example.util.ForegroundService
import com.example.util.retrofit.ClientInterceptor
import com.example.view.add_fragment.AddFragment
import com.example.view.favourite_fragment.FavouriteFragment
import com.example.view.home_fragment.HomeFragment
import com.example.view.profile_fragment.ProfileFragment
import com.example.view.search_fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.second_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        startServices()

        val bottomNavigationMenu : BottomNavigationView = findViewById(R.id.nav_view)

     //   bottomNavigationMenu.getOrCreateBadge(R.id.badge).number = 2

        if(savedInstanceState == null)
            loadFragment(HomeFragment())

        bottomNavigationMenu.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.Profile -> {
                    loadFragment(ProfileFragment())
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

    fun startServices() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        startService(serviceIntent)
    }

    fun stopServices() {
        val serviceIntent = Intent(applicationContext, ForegroundService::class.java)
        stopService(serviceIntent)
    }
}