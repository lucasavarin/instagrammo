package com.example.instagrammo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.response.AuthResponse
import com.example.instagrammo.retrofit.Client
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_submit.setOnClickListener{ view-> doLogin()}
    }

    fun doLogin(){
        val call = Client.getClient.doAuth(AuthRequest(email_label.text.toString(), label_pwd.text.toString()))

        call.enqueue(object: Callback<AuthResponse>{
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"'error",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful){
                    Toast.makeText(applicationContext,"Successo",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext,"Fallimento",Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}