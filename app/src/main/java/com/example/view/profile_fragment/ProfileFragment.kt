package com.example.view.profile_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.*
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import com.example.util.retrofit.Session
import com.example.view.home_fragment.CircleTransform
import com.example.view.home_fragment.HomeFollowerStoryAdapter
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.android.synthetic.main.profile_layout.*
import kotlinx.android.synthetic.main.profile_layout.profileImg
import kotlinx.android.synthetic.main.profile_layout.view.profileImg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.profile_layout,container ,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = activity?.supportFragmentManager?.let { PostsViewAdapter(it) }
        pager.adapter = adapter
        pager.offscreenPageLimit = 1
        tab_layout.setupWithViewPager(pager)


        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })



        modifyButton.setOnClickListener{

            val fragment = ModifyUserFragment()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        ClientInterceptor.getUser.getProfile(Session.profileId).enqueue(object : Callback<ProfileWrapperResponse>{

            override fun onFailure(call: Call<ProfileWrapperResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProfileWrapperResponse>,
                response: Response<ProfileWrapperResponse>
            ) {
                if (response.isSuccessful){
                    if(!response.body()?.payloadProfile?.isEmpty()!!) {
                        putUserInfo(response.body()!!.payloadProfile[0])

                    }

                }

            }
        })







    }

    private fun putUserInfo(response: HomeProfilePostBean) {

        numFriends.text = response.followersNumber
        numposts.text = response.postsNumber
        Picasso.get().load(response.picture).transform(CircleTransform()).into(profileImg.profileImg)
        profileName.text = response.name
        profileDescr.text = response.description

    }


}