package com.instagrammo.view.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.instagrammo.bean.buissnes.HomeWrapperPostBean
import com.instagrammo.bean.buissnes.HomeWrapperResponse
import com.instagrammo.util.retrofit.ClientInterceptor
import com.example.login.R
import com.instagrammo.util.database.DataBaseHelper
import com.instagrammo.util.shared_prefs.prefsDataBase
import com.instagrammo.view.profile_fragment.ProfileFragment
import kotlinx.android.synthetic.main.home_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        ClientInterceptor.getUser.getFollowers().enqueue(object : Callback<HomeWrapperResponse>{

            override fun onFailure(call: Call<HomeWrapperResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<HomeWrapperResponse>,
                response: Response<HomeWrapperResponse>
            ) {
               createFollowerStories(response)
            }
        })

        ClientInterceptor.getUser.getFollowerPost().enqueue(object : Callback<HomeWrapperPostBean>{
            override fun onFailure(call: Call<HomeWrapperPostBean>, t: Throwable) {
                val linearLayoutManager = LinearLayoutManager(context)
                HomeFollowerPosts.layoutManager = linearLayoutManager
                HomeFollowerPosts.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                val adapterFollowerPost = HomeFollowerPostAdapter(prefsDataBase.readPostData())
                HomeFollowerPosts.adapter = adapterFollowerPost

            }

            override fun onResponse(
                call: Call<HomeWrapperPostBean>,
                response: Response<HomeWrapperPostBean>
            ) {
                createFollowerPost(response)
                prefsDataBase.insertPostData(response.body()!!.payload)
            }

        })

        return inflater.inflate(R.layout.home_layout,container,false)

    }


    private fun createFollowerStories(response: Response<HomeWrapperResponse>) : RecyclerView {

        val linearLayoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        HomeFollowerStory.layoutManager = linearLayoutManager
        HomeFollowerStory.setHasFixedSize(true)
        val adapterFollowers = HomeFollowerStoryAdapter(response.body()!!.payload)

        adapterFollowers.callBackProfile {
            val profileFragment : Fragment = ProfileFragment.newInstance(it.id)
            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,profileFragment).addToBackStack(null).commit()
        }

        HomeFollowerStory.adapter = adapterFollowers


        return HomeFollowerStory
    }

    private fun createFollowerPost(response : Response<HomeWrapperPostBean>) : RecyclerView {
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.stackFromEnd = true
        linearLayoutManager.reverseLayout = true
        HomeFollowerPosts.layoutManager = linearLayoutManager
        HomeFollowerPosts.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        val adapterFollowerPost = HomeFollowerPostAdapter(response.body()!!.payload)
        HomeFollowerPosts.adapter = adapterFollowerPost
        return HomeFollowerPosts
    }


}