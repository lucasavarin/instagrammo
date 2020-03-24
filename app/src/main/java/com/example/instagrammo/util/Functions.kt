package com.example.instagrammo.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.activity_main.*

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

fun addFragment(fragment : Fragment, activity : FragmentActivity) {
    val fragmentManager = activity.supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.container, fragment)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()
}

fun deleteFragment(fragment: Fragment, activity: FragmentActivity){
    val fragmentManager = activity.supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.remove(fragment)
    fragmentTransaction.commit()
    fragmentManager.popBackStack()
}

