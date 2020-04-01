package com.example.instagrammo.recyclerview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.instagrammo.secondary_fragments.GridLayoutFragment
import com.example.instagrammo.secondary_fragments.ListLayoutFragment

class TabLayoutAdapter(fM: FragmentManager, private val profileId : String) : FragmentStatePagerAdapter(fM, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs = arrayOf("","")

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                ListLayoutFragment.makeInstance(profileId)
            }
            1->{
                GridLayoutFragment.makeInstance(profileId)
            }
            else -> ListLayoutFragment.makeInstance(profileId)
        }
    }

    override fun getCount(): Int = tabs.size
}