package com.instagrammo.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.login.R
import java.util.*
import java.util.concurrent.ThreadLocalRandom

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

    }



}