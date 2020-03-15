package com.example.instagrammo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R

class SplashScreenActivity : AppCompatActivity(){

    private val SPLASH_TIME_OUT:Long=2000 // 2 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this,
                LoginActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)

    }
}