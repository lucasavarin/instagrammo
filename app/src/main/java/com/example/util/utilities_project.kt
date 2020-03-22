package com.example.util

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.login.R
import java.util.*

class utilities_project {

    companion object Utilities {

        fun addFragment(fragment : Fragment, activity : FragmentActivity) {

            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        fun deleteFragment (fragment : Fragment, activity : FragmentActivity) {

            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment)
            fragmentTransaction.commit()
            fragmentManager.popBackStack()

        }

        fun generateRandomNumbers(from: Int, to: Int) : Int {

            val random = Random()

            return random.nextInt(to - from) + from

        }
    }



}