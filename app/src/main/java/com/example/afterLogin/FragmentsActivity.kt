package com.example.afterLogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.HomeFragment.HomeFragment
import com.example.login.R

import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val bottomNavigationMenu : BottomNavigationView = findViewById(R.id.nav_view)

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

                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }



}