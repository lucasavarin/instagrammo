package thushyanthan.scott.javalynx.instagrammo.activities

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.ApiClient
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.util.rest.AuthResponse
import thushyanthan.scott.javalynx.instagrammo.util.rest.DataRequest
import thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs.prefs

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        login_show_password.setOnCheckedChangeListener({_ ,isChecked->
            if (isChecked == true){
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
            }else{
                password.setTransformationMethod(PasswordTransformationMethod.getInstance())
            }
        })

        loginButton.setOnClickListener { view ->

            val call = ApiClient.getClient.doAuth(
                DataRequest(
                    username.text.toString(),
                    password.text.toString()
                )
            )


            call.enqueue(object : Callback<AuthResponse> {
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.ERROR
                }

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {

                    if (response.body()?.authToken != null && response.body()?.profileID != null){
                        Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
                        val intent =  Intent(applicationContext,
                            MainActivity::class.java)
                        startActivity(intent)
                        prefs.username= username.text.toString()
                        prefs.password = password.text.toString()
                        prefs.token = response.body()?.authToken.toString()
                        prefs.profiloUtente = response.body()?.profileID.toString()

                        if (login_switch.isActivated){
                            val user: EditText = username
                            user.setText(prefs.username)
                            val pw : EditText = password
                            pw.setText(prefs.password)
                        }
                    }

                }



            })
        }
    }


}