package com.lynxspa.instagrammo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.adapters.CustomAdapter
import com.lynxspa.instagrammo.db
import com.lynxspa.instagrammo.model.AppDataBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter:CustomAdapter = CustomAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayout = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = linearLayout
        recyclerview.adapter = adapter
        adapter.setOnItemLongClickListener {
            db.deleteRecord(it)
            updateData()
        }
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
        updateData()
    }

    private fun updateData(){
        adapter.list = db.query().toMutableList()
        adapter.notifyDataSetChanged()
    }
}