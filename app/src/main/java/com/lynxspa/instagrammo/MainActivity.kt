package com.lynxspa.instagrammo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_view_header.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        passActivity()
    }

    private fun passActivity() {
        Button.setOnClickListener(this::passActivity2)
    }
    private fun passActivity2(view : View) : Unit {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        startActivity(intent)

    }

}
