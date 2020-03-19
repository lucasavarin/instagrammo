package com.example.view.profile_fragment

import android.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PostsViewAdapter(fragmentPagerAdapter: FragmentManager) : FragmentPagerAdapter(fragmentPagerAdapter) {

    val tabTitles = arrayOf("list", "grid")
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                println("Griglia")
                return ProfileGridFragment(false)
            }

            1 -> {
                println("Griglia")
                return ProfileGridFragment(true)
            }
            else -> return ProfileGridFragment(true)
        }


    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }

    override fun getCount(): Int {
        return tabTitles.size
    }
}