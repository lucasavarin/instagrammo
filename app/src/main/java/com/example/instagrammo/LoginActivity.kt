package com.example.instagrammo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.response.AuthResponse
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.Session
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity:AppCompatActivity() {

    val ctx = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bn_login.setOnClickListener { view -> doLogin() }
    }



    fun doLogin(){
        val call = RetrofitController.getClient.doAuth(AuthRequest(username.text.toString(), password.text.toString()))

        call.enqueue(object: Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        Toast.makeText(ctx, "Autenticazione riuscita", Toast.LENGTH_SHORT).show()
                        Session.user = username.text.toString()
                        Session.token = body.authToken
                        Session.profileId = body.profileId

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(ctx, "Autenticazione fallita", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(ctx, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(ctx, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }
        })
    }
}