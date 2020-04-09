package com.example.instagrammo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import com.example.instagrammo.model.rest.response.AuthResponse
import com.example.instagrammo.model.business.Session
import com.example.instagrammo.model.rest.request.User
import com.example.instagrammo.util.prefs
import com.example.instagrammo.util.retrofit.RetrofitController
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private var isShowPsw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        val retrofit = RetrofitController.getClient
        supportActionBar?.hide()
        setData()

        //Show/Hide button
        show_pass_btn.setOnClickListener {
            isShowPsw = !isShowPsw
            showpsw(isShowPsw)
        }
        showpsw(isShowPsw)


        Log.d("NOME", editText.text.toString() + " " + editTextpwd.text.toString())
        btn.setOnClickListener { v ->
            val call: Call<AuthResponse> =
                retrofit.auth(
                    User(
                        editText.text.toString(),
                        editTextpwd.text.toString()
                    )
                )
            progressBar1.visibility = View.VISIBLE
            btn.visibility = View.GONE
            call.enqueue(object : Callback<AuthResponse> {
                override fun onFailure(call: Call<AuthResponse>?, t: Throwable?) {
                    Log.d("RESPONSE", call.toString())
                    progressBar1.visibility = View.GONE
                    btn.visibility = View.VISIBLE
                    Toast.makeText(applicationContext, "Chiamata non riuscita", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<AuthResponse>?,
                    response: Response<AuthResponse>?
                ) {
                    val valori: AuthResponse =
                        AuthResponse(
                            response?.body()!!.result,
                            response?.body()!!.authToken,
                            response?.body()!!.profileId
                        )
                    progressBar1.visibility = View.GONE
                    btn.visibility = View.VISIBLE
                    Session.user = editText.text.toString()
                    val intent: Intent = Intent(
                        applicationContext,
                        MainActivity::class.java
                    )
                    if (valori.authToken != null && valori.profileId != null) {
                        Session.token = valori.authToken
                        Session.profileId = Integer.parseInt(valori.profileId)
                        setSharedPreferenceData()
                        startActivity(intent)
                        this@LoginActivity.finish()
                    } else {
                        Toast.makeText(applicationContext, "Credenziali Errate", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
    }

    private fun setData() {
        remem.isChecked = prefs.rememberMe

        if (remem.isChecked) {
            editText.setText(prefs.username)
        }
    }

    private fun setSharedPreferenceData() {

        if (remem.isChecked) {
            prefs.username = editText.text.toString()
            prefs.rememberMe = true
        } else {
            prefs.username = ""
            prefs.rememberMe = false
        }
    }

    private fun showpsw(isShow: Boolean) {
        if (isShow) {
            editTextpwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            show_pass_btn.setImageResource(R.drawable.ic_visibility_off_24dp)
        } else {
            editTextpwd.transformationMethod = PasswordTransformationMethod.getInstance()
            show_pass_btn.setImageResource(R.drawable.ic_show_psw_24dp)
        }
        editTextpwd.setSelection(editTextpwd.text.toString().length)
    }
}