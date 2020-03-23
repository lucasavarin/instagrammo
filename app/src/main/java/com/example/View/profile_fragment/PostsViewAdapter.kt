package com.example.view.profile_fragment

import android.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter


class PostsViewAdapter(fragmentPagerAdapter: FragmentManager) : FragmentStatePagerAdapter(fragmentPagerAdapter) {

    val tabTitles = arrayOf("list", "grid")
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                println("Lista")
                return ProfileListFragment()
            }

            1 -> {
                println("Griglia")
                return ProfileGridFragment()
            }
            else -> return ProfileListFragment()
        }


    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }

    override fun getCount(): Int {
        return tabTitles.size
    }
}