package com.example.instagrammo.primary_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.secondary_fragments.EditProfileFragment
import com.example.instagrammo.R
import com.example.instagrammo.recyclerview.TabLayoutAdapter
import com.example.instagrammo.beans.response.ProfileWrapperBean
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.retrofit.Session
import com.example.instagrammo.util.CircleTrasformation
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment(private val profileId : String): Fragment() {

    private lateinit var adapter : TabLayoutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        makeCall()
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TabLayoutAdapter(childFragmentManager, this.profileId)
        viewer.adapter = adapter

        viewer.offscreenPageLimit = 1
        navigation_tab.setupWithViewPager(viewer)
        navigation_tab.getTabAt(0)?.setIcon(R.drawable.baseline_view_stream_black_18)
        navigation_tab.getTabAt(1)?.setIcon(R.drawable.baseline_apps_black_18)

        navigation_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewer.currentItem = tab!!.position
            }

        })

        if (this.profileId == Session.profileId){
            edit_profile.visibility = View.VISIBLE
        }else{
            edit_profile.visibility = View.GONE
        }
        edit_profile.setOnClickListener{
            val fragment = EditProfileFragment()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    private fun makeCall(){
        Client.getClient.getProfile(this.profileId).enqueue(object : Callback<ProfileWrapperBean>{
            override fun onFailure(call: Call<ProfileWrapperBean>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProfileWrapperBean>,
                response: Response<ProfileWrapperBean>
            ) {
                if (response.isSuccessful){
                    fillData(response)
                }
            }

        })
    }

    private fun fillData(response : Response<ProfileWrapperBean>){
        Picasso.get().load(response.body()!!.payload[0].picture).transform(CircleTrasformation()).into(profile_img)
        nPost_text.text = response.body()!!.payload[0].postsNumber
        desc_profile.text = response.body()!!.payload[0].description
        friend_text.text = response.body()!!.payload[0].followersNumber
        name_profile.text = response.body()!!.payload[0].name
    }

    companion object{
        fun makeInstance(profileId: String = Session.profileId): UserFragment {
            return UserFragment(profileId)
        }
    }
}