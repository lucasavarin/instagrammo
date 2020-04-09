package com.example.instagrammo.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.instagrammo.R

fun AppCompatActivity.makeTransaction(f: Fragment) {
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()
    if (f != null) {
        transaction.replace(R.id.frame, f)
        transaction.commit()
    }
}