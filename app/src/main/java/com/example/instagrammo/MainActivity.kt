package com.example.instagrammo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.home_fragment.HomeFragment
import com.example.instagrammo.util.addFragment
import com.example.instagrammo.util.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(HomeFragment.makeInstance(), R.id.container)

        navView.setOnNavigationItemSelectedListener { menuItem : MenuItem ->
            when(menuItem.itemId){
                R.id.home ->{
                    replaceFragment(HomeFragment.makeInstance(), R.id.container)
                }
                R.id.search ->{
                    replaceFragment(SearchFragment.makeInstance(), R.id.container)
                }
                R.id.user ->{
                    replaceFragment(UserFragment.makeInstance(), R.id.container)
                }
                R.id.add ->{
                    replaceFragment(AddFragment.makeInstance(), R.id.container)
                }
                R.id.favorite ->{
                    replaceFragment(FavoriteFragment.makeInstance(), R.id.container)
                }
                else -> false
            }
        }
    }
}