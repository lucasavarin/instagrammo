package com.example.instagrammo.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit){
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment : Fragment, frameId : Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment : Fragment, frameId : Int): Boolean{
    supportFragmentManager.inTransaction { add(frameId, fragment) }
    return true
}

