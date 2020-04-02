package com.mst.instagrammo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mst.instagrammo.R
import com.mst.instagrammo.api.ApiClient
import com.mst.instagrammo.model.AuthRequest
import com.mst.instagrammo.model.AuthResponse
import com.mst.instagrammo.utilities.Session
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener{ doLogin()}
    }

    fun doLogin(){
        val user = editTextUser.text.toString()
        val pswd = editTextPswd.text.toString()
        val call = ApiClient.getClient.doAuth(AuthRequest(user, pswd))

        call.enqueue(object: Callback<AuthResponse>{
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful){
                    var body = response.body()!!
                    if(body.result){
//                        Toast.makeText(applicationContext, "its ok", Toast.LENGTH_LONG).show()
                        Session.user = user
                        Session.authToken = body.authToken.toString()
                        Session.profileId = body.profileId!!.toInt()

                        intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        this@LoginActivity.finish()
                    }
                    else{
                        Toast.makeText(applicationContext, "User Incorrect", Toast.LENGTH_LONG).show()
                    }
                }
                else {
                    Toast.makeText(applicationContext, "Not Successful", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

//    fun setDataRememberMe(){
//        remember.isChecked = prefs.rememberMe
//        if (remember.isChecked) {
//            user.setText(prefs.user)
//            password.setText(prefs.password)
//        }
//    }
//
//    fun setSharedPreferenceData(){
//        if(remember.isChecked) {
//            prefs.user = user.text.toString()
//            prefs.password = password.text.toString()
//            prefs.rememberMe = true
//        } else{
//            prefs.user = ""
//            prefs.password = ""
//            prefs.rememberMe = false
//        }
//    }

}
