package com.example.instagrammo

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val nome :EditText= findViewById<EditText>(R.id.editText)
        nome.setTextColor(Color.RED)
        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener{
            Toast.makeText(this,"Ciao ${nome.text}",Toast.LENGTH_SHORT).show()
        }

    }
}