package com.mst.instagrammo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.se.omapi.Session
import android.widget.Toast
import com.mst.instagrammo.R
import com.mst.instagrammo.api.ApiClient
import com.mst.instagrammo.beans.AuthRequest
import com.mst.instagrammo.beans.AuthResponse
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Cookie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonSignUp.setOnClickListener{view -> doLogin()}
    }

    fun doLogin(){
        val user = editTextUser.text.toString()
        val pswd = editTextPswd.text.toString()
        val call = ApiClient.getClient.doAuth(AuthRequest(user, pswd))

        call.enqueue(object: Callback<AuthResponse>{
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                    //TODO save in session
                    //TODO open main activity
                }
                else {
                    Toast.makeText(applicationContext, "is not successful", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}
