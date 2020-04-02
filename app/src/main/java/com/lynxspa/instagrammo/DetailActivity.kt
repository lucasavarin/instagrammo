package com.lynxspa.instagrammo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.custom_view_header.*

class DetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getBackToMainActivity()
    }

    fun getBackToMainActivity(){
        headerCustomView.setOnBackClickListener {
            finish()
        }
    }

}