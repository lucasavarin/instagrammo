package com.lynxspa.instagrammo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.db
import com.lynxspa.instagrammo.model.AppDataBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lista: MutableList<AppDataBean> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        passActivity()
    }

    private fun passActivity() {
        Button.setOnClickListener(this::passActivity2)
    }
    private fun passActivity2(view : View) : Unit {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        lista = db.query().toMutableList()
        for(bean in lista){
            Log.wtf("LISTA_BEAN", bean.toString())
        }
    }

}
