package com.example.view.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.bean.buissnes.HomeWrapperPostBean
import com.example.bean.buissnes.HomeWrapperResponse
import com.example.util.retrofit.ClientInterceptor
import com.example.login.R
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

            }

            override fun onResponse(
                call: Call<HomeWrapperPostBean>,
                response: Response<HomeWrapperPostBean>
            ) {
                createFollowerPost(response)
            }

        })

        return inflater.inflate(R.layout.home_layout,container,false)

    }


    private fun createFollowerStories(response: Response<HomeWrapperResponse>) : RecyclerView {

        val linearLayoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        HomeFollowerStory.layoutManager = linearLayoutManager
        HomeFollowerStory.setHasFixedSize(true)
        val adapterFollowers =
            HomeFollowerStoryAdapter(response.body()!!.payload)
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