package com.mst.instagrammo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mst.instagrammo.R
import com.mst.instagrammo.api.AppClient
import com.mst.instagrammo.beans.AuthRequest
import com.mst.instagrammo.beans.AuthResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonSignUp.setOnClickListener{
            val user = editTextUser.text.toString()
            val pswd = editTextPswd.text.toString()

            AppClient.instance.doAuth(AuthRequest(user, pswd)).enqueue(object: Callback<AuthResponse>{
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Toast.makeText(applicationContext, "noooo va bene", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}
