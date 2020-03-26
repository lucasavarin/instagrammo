package com.mst.instagrammo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mst.instagrammo.activities.fragments.secondaries.ProfileGridFragment
import com.mst.instagrammo.activities.fragments.secondaries.ProfileListFragment

class ProfilePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 2

    override fun getItem(i: Int): Fragment {
        var fragment = Fragment()

        when (i) {
            0 -> { fragment = ProfileGridFragment() }
            1 -> { fragment = ProfileListFragment() }
        }
        return fragment
    }
}
