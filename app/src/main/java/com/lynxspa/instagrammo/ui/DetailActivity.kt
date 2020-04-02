package com.lynxspa.instagrammo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.db
import com.lynxspa.instagrammo.model.AppDataBean
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getBackToMainActivity()

        save_btn.setOnClickListener{

            val bean = AppDataBean(
                edit_1.text.toString(),
                edit_2.text.toString())

            db.insert(bean)
            finish()
        }

    }

    fun getBackToMainActivity(){
        headerCustomView.setOnBackClickListener {
            finish()
        }

    }

}