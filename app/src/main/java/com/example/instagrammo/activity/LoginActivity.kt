package com.example.instagrammo.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.ForegroundService
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
        remember_me.isChecked = prefs.remember_user

        btn_submit.setOnClickListener{ view-> doLogin()}
        remember_me.setOnCheckedChangeListener{buttonView, isChecked ->
            prefs.remember_user = isChecked
        }
//        managePrefs()
    }

    fun doLogin(){
        val call = Client.getClient.doAuth(AuthRequest(email_label.text.toString(), label_pwd.text.toString()))

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
                            startActivity(intent)
                            finish()
                            initService()
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

    fun initService(){
        val postNumberService = Intent(applicationContext, ForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(postNumberService)
        }else{
            startService(postNumberService)
        }
    }
//    fun managePrefs(){
//        val prefsUser = getSharedPreferences("com.example.instagrammo.shared_prefs.prefs", Context.MODE_PRIVATE)
//        val editor = prefsUser.edit()
//        if (remember_me.isChecked){
//            editor.putString("username", email_label.toString())
//            editor.putString("password", label_pwd.toString())
//            editor.apply()
//        }else{
//            editor.remove("username")
//            editor.remove("password")
//            editor.apply()
//        }
//    }
}