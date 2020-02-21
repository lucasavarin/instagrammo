package thushyanthan.scott.javalynx.instagrammo

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.fragments.HomeFragment

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sp = SharedPrefs(context = applicationContext)

        if(!sp.getValueString("username").equals("") && !sp.getValueString("password").equals("") ){
            val user: EditText = username
            user.setText(sp.getValueString("username"))
            val pw : EditText = password
            pw.setText(sp.getValueString("password"))
        }

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
                        val intent =  Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                        sp.save("username", username.text.toString())
                        sp.save("password", password.text.toString())
                    }

                }



            })
        }
    }

    fun userRepo(): SharedPreferences{
        val  sharedPref: SharedPreferences = getSharedPreferences("com.prova.instagrammo", 0)
        sharedPref.edit().putString("username", username.text.toString()).commit();
        sharedPref.edit().putString("password", username.text.toString()).commit();
        return sharedPref
    }
}