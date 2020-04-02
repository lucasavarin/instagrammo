package com.example.instagrammo.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.lang.NumberFormatException

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

fun normalizeDownloadUrlToScreenWidth(downloadUrl:String, screenWidth:Int):String{
    val split = downloadUrl.split("/")
    var final = ""
    for(index in 0..split.size-3){
        final += split[index] + "/"
    }

    val height: Float
    try {
        val ratio:Float = screenWidth/split[split.size-2].toFloat()
        height = split[split.size-1].toFloat()*ratio
        final += "${screenWidth}/${height.toInt()}"
    } catch (e: NumberFormatException) {
        final = downloadUrl
    }
    return final
}

fun isNetworkAvailable(context: Context): Boolean {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

const val NO_IMAGE_AVAILABLE = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png"
const val NO_PROFILE_PIC = "https://moonvillageassociation.org/wp-content/uploads/2018/06/default-profile-picture1.jpg"