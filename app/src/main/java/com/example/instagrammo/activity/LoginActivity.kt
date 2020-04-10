package com.example.instagrammo.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.response.AuthResponse
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.retrofit.Session
import com.example.instagrammo.shared_prefs.prefs
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setRememberMe()

        btn_submit.setOnClickListener{ view-> doLogin()}
    }

    fun doLogin(){
        val user = user_label.text.toString()
        val pwd = label_pwd.text.toString()

        val call = Client.getClient.doAuth(AuthRequest(user, pwd))

        call.enqueue(object: Callback<AuthResponse>{
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"'error",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful){
                    if (response.body()!= null) {
                        val body = response.body()!!
                        if(body.result) {
                            Session.token = response.body()!!.authToken
                            Session.profileId = response.body()!!.profileId
                            intent = Intent(applicationContext, MainActivity::class.java)
                            setDataToLabels()
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    Toast.makeText(applicationContext,"Fallimento",Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.hide()
    }

    private fun setRememberMe(){
        remember_me.isChecked = prefs.remember_user
        if (remember_me.isChecked){
            user_label.setText(prefs.user)
            label_pwd.setText(prefs.password)
        }
    }

    private fun setDataToLabels(){
        if (remember_me.isChecked){
            prefs.user = user_label.text.toString()
            prefs.password = label_pwd.text.toString()
            prefs.remember_user = true
        }else{
            prefs.user = ""
            prefs.password = ""
            prefs.remember_user = false
        }
    }
}