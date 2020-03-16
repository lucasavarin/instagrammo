package com.example.view.profile_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.HomeProfilePostBean
import com.example.bean.buissnes.HomeWrapperResponse
import com.example.bean.buissnes.ProfileImgWrapper
import com.example.view.modifyUserFragment.ModifyUserFragment
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import com.example.util.retrofit.Session
import com.example.view.home_fragment.CircleTransform
import com.example.bean.buissnes.ProfileWrapperResponse
import com.example.view.home_fragment.HomeFollowerStoryAdapter
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
                        putImg(response.body()!!.payloadProfile)
                    }

                }

            }
        })

//        ClientInterceptor.getUser.getPosts("5", "6").enqueue(object : Callback<ProfileImgWrapper>){
//
//
//        }





    }

    private fun putUserInfo(response: HomeProfilePostBean) {

        numFriends.text = response.followersNumber
        numposts.text = response.postsNumber
        Picasso.get().load(response.picture).transform(CircleTransform()).into(profileImg.profileImg)
        profileName.text = response.name
        profileDescr.text = response.description

    }

    private fun putImg(response: List<HomeProfilePostBean>) : RecyclerView {
        val linearLayoutManager = LinearLayoutManager(this.context,
            LinearLayoutManager.HORIZONTAL,false)
        profileRecycleView.layoutManager = linearLayoutManager
        profileRecycleView.setHasFixedSize(true)
        val adapterFollowers =
            ProfilePostAdapter(response)
        profileRecycleView.adapter = adapterFollowers

        return profileRecycleView
    }
}