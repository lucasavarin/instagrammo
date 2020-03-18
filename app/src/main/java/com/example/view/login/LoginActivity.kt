package com.example.view.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.bean.rest.request.AuthRequest
import com.example.bean.rest.response.AuthResponse
import com.example.util.retrofit.ClientInterceptor
import com.example.util.retrofit.Session
import com.example.login.*
import com.example.util.shared_prefs.prefs
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, FragmentsActivity::class.java)

        setDataRememberMe()

        login.setOnClickListener{
            val user = user.text.toString()
            val pass = password.text.toString()

            ClientInterceptor.getLogin.getUser(
                AuthRequest(user, pass)
            ).enqueue(object : Callback<AuthResponse>{
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
                            setSharedPreferenceData()

                            startActivity(intent)
                            finish()
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

    private fun setDataRememberMe(){

        remember.isChecked = prefs.rememberMe

        if (remember.isChecked) {
            user.setText(prefs.user)
            password.setText(prefs.password)
        }
    }

    private fun setSharedPreferenceData(){

        if(remember.isChecked) {
            prefs.user = user.text.toString()
            prefs.password = password.text.toString()
            prefs.rememberMe = true

        } else{
            prefs.user = ""
            prefs.password = ""
            prefs.rememberMe = false
        }
    }

}



