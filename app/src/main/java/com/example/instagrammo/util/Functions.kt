package com.example.instagrammo.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit){
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag:String): Unit{
    supportFragmentManager.inTransaction { add(frameId, fragment, tag) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, tag: String): Unit{
    supportFragmentManager.inTransaction { replace(frameId, fragment, tag) }
}

fun AppCompatActivity.getFragmentByTag(tag: String):Fragment? {
    return supportFragmentManager.findFragmentByTag(tag)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}