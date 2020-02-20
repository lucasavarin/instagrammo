package com.example.instagrammo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.response.AuthResponse
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.shared_prefs.Prefs
import com.example.instagrammo.shared_prefs.prefs
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        remember_me.isChecked = prefs.remember_user

        btn_submit.setOnClickListener{ view-> doLogin()}
        remember_me.setOnCheckedChangeListener{buttonView, isChecked ->
            prefs.remember_user = isChecked
        }
    }

    fun doLogin(){
        val call = Client.getClient.doAuth(AuthRequest(email_label.text.toString(), label_pwd.text.toString()))

        call.enqueue(object: Callback<AuthResponse>{
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"'error",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful){
                    intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(applicationContext,"Successo",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext,"Fallimento",Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}