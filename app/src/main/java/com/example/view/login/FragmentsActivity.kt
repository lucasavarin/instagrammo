package com.example.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.login.R
import com.example.view.add_fragment.AddFragment
import com.example.view.favourite_fragment.FavouriteFragment
import com.example.view.home_fragment.HomeFragment
import com.example.view.profile_fragment.ProfileFragment
import com.example.view.search_fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val bottomNavigationMenu : BottomNavigationView = findViewById(R.id.nav_view)

        if (savedInstanceState == null)
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

    private fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }
}