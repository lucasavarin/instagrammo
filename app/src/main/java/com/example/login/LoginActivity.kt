package com.example.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.afterLogin.FragmentsActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, FragmentsActivity::class.java)

        login.setOnClickListener{
            val username = user.text.toString()
            val password = password.text.toString()

            ClientInterceptor.getUser.getUser(AuthRequest(username,password)).enqueue(object : Callback<AuthResponse>{
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "hai sbagliato credenziali", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>) {
                    if(response.isSuccessful){
                        if(response.body()?.result == true){
                            Session.token = response.body()!!.token
                            Session.profileId = response.body()!!.profileId
                            startActivity(intent)
                        } else{
                            Toast.makeText(this@LoginActivity, "hai sbagliato credenziali", Toast.LENGTH_LONG).show()
                        }
                    }
                    else {
                        Toast.makeText(this@LoginActivity, "Il servizio non è disponibile", Toast.LENGTH_LONG).show()
                    }

                }
            })
        }

    }
}
