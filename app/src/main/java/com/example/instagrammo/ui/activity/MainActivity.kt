package com.example.instagrammo.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.instagrammo.*
import com.example.instagrammo.ui.fragment.*
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(applicationContext)
        setContentView(R.layout.main_activity)
        startServices()
        var fragment: Fragment? = null
        if (savedInstanceState == null){
            makeTransaction(HomeFragment.newInstance())
        }


        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.header)

        val profileId = intent.extras?.get("profileId")
        if(profileId!= null){
            val b = Bundle()
            b.putString("profileId",profileId.toString())
            val f = ProfiloFragment.newInstance()
            f.arguments = b
            makeTransaction(f)
        }
        bottom.setOnNavigationItemSelectedListener {


            when (it.itemId) {
                R.id.home -> {
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
    fun startServices() {
        val intent = Intent(this, PostNumberService::class.java)
        startService(intent)
    }

    fun stopServices() {
        val intent = Intent(applicationContext, PostNumberService::class.java)
        stopService(intent)
    }

}