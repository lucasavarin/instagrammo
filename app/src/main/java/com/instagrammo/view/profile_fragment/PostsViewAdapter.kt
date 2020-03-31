package com.instagrammo.view.profile_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter



class PostsViewAdapter(fragmentPagerAdapter: FragmentManager, private val profileId : String) : FragmentStatePagerAdapter(fragmentPagerAdapter) {

    val tabTitles = arrayOf("list", "grid")
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                println("Lista")
                return ProfileListFragment.newInstance(profileId)
            }

            1 -> {
                println("Griglia")
                return ProfileGridFragment.newInstance(profileId)
            }
            else -> return ProfileListFragment.newInstance(profileId)
        }


    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }

    override fun getCount(): Int {
        return tabTitles.size
    }
}