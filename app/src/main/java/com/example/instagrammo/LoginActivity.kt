package com.example.instagrammo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_submit.setOnClickListener{
            val email = email_label.text
            val pwd = label_pwd.text

            if(!email!!.contains("@")){
                Toast.makeText(this, "Email errata", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, email, Toast.LENGTH_SHORT).show()
            }
        }
    }
}