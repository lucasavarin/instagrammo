package com.example.instagrammo

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.Loader
import kotlinx.android.synthetic.main.login_activity.*
import model.AuthResponse
import model.Session
import model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private var isShowPsw= false
    val ctx = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        val retrofit = RetrofitController.getClient

        //Show/Hide button
        show_pass_btn.setOnClickListener{
            isShowPsw = !isShowPsw
            showpsw(isShowPsw)
        }
            showpsw(isShowPsw)

        //SharedPref
        btn.setOnClickListener { view -> doLogin() }

        if (save.isChecked){

            save.isChecked = prefs.rememberMe
            editText.setText(prefs.username)
        }

       /* save.setOnCheckedChangeListener { _, isChecked ->
            prefs.rememberMe = isChecked
        }
        */

        Log.d("NOME", editText.text.toString() + " " +editTextpwd.text.toString())
        btn.setOnClickListener { v ->
            val call: Call<AuthResponse> =
                retrofit.auth(User(editText.text.toString(),editTextpwd.text.toString()))
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
                    val valori:AuthResponse= AuthResponse(response?.body()!!.result,response?.body()!!.authToken,response?.body()!!.profileId)
                    progressBar1.visibility = View.GONE
                    btn.visibility = View.VISIBLE
                    val bundle  = Bundle()
                    bundle.putString("profiloId",valori.profileId)
                    val intent : Intent = Intent(applicationContext,MainActivity::class.java)
                    if(valori.authToken!=null && valori.profileId!=null)
                            startActivity(intent,bundle)
                    else{
                        Toast.makeText(applicationContext, "Credenziali Errate", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
    }
    private fun showpsw(isShow: Boolean){
        if(isShow){
            editTextpwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            show_pass_btn.setImageResource(R.drawable.ic_visibility_off_24dp)
        }else{
            editTextpwd.transformationMethod = PasswordTransformationMethod.getInstance()
            show_pass_btn.setImageResource(R.drawable.ic_show_psw_24dp)
        }
        editTextpwd.setSelection(editTextpwd.text.toString().length)
    }

    private fun doLogin(){
        val call = RetrofitController.getClient.auth(User(editText.text.toString(), editTextpwd.text.toString()))

        call.enqueue(object: Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result!!){
                        Toast.makeText(ctx, "Autenticazione riuscita", Toast.LENGTH_SHORT).show()
                        Session.user = editText.text.toString()
                        Session.token = body.authToken.orEmpty()
                        Session.profileId = body.profileId.hashCode()

                        if(save.isChecked) {
                            prefs.username = editText.text.toString()

                        } else{
                            prefs.username = ""
                        }
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(ctx, "Autenticazione fallita", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(ctx, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(ctx, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }
        })
    }
}