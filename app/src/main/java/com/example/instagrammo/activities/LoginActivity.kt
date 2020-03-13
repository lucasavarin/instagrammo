package com.example.instagrammo.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import com.example.instagrammo.applicationUtils.prefs
import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.response.AuthResponse
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.Session
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bn_login.setOnClickListener { view -> doLogin() }

        checkbox.isChecked = prefs.rememberMe

        if (checkbox.isChecked){
            username.setText(prefs.username)
            password.setText(prefs.password)
        }

        checkbox.setOnCheckedChangeListener { _, isChecked ->
            prefs.rememberMe = isChecked
        }
    }



    private fun doLogin(){
        val call = RetrofitController.getClient.doAuth(AuthRequest(username.text.toString(), password.text.toString()))

        call.enqueue(object: Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        Toast.makeText(applicationContext, "Autenticazione riuscita", Toast.LENGTH_SHORT).show()
                        Session.user = username.text.toString()
                        Session.token = body.authToken
                        Session.profileId = body.profileId

                        if(checkbox.isChecked) {
                            prefs.username = username.text.toString()
                            prefs.password = password.text.toString()
                        } else{
                            prefs.username = ""
                            prefs.password = ""
                        }
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Autenticazione fallita", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }
        })
    }
}