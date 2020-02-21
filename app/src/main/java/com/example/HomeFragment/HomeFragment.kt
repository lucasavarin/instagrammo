package com.example.HomeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login.AuthResponse
import com.example.login.ClientInterceptor
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

        return inflater.inflate(R.layout.home_layout,container,false)

    }


    private fun createFollowerStories(response: Response<HomeWrapperResponse>) : RecyclerView {

        val linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        HomeFollowerStory.layoutManager = linearLayoutManager
        val adapterFollowers = RecycleViewVerticalAdapter(response.body()!!.payload)
        HomeFollowerStory.adapter = adapterFollowers

        return HomeFollowerStory
    }


}