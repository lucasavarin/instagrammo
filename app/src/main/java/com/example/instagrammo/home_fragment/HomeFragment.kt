package com.example.instagrammo.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.HomePostAdapter
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.HomeWrapperPostBean
import com.example.instagrammo.retrofit.Client
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response

class HomeFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
//        return inflater.inflate(R.layout.fragment_home, container, false)

        Client.getClient.getFollowersPosts().enqueue(object : retrofit2.Callback<HomeWrapperPostBean>{
            override fun onFailure(call : Call<HomeWrapperPostBean>, t : Throwable){

            }
            override fun onResponse(
                call: Call<HomeWrapperPostBean>,
                response: Response<HomeWrapperPostBean>
            ){
                createPost(response)
            }
        })
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object{
        fun makeInstance():Fragment {
            return HomeFragment()
        }
    }

    private fun createPost(response: Response<HomeWrapperPostBean>) : RecyclerView{
        val linearLayoutManager = LinearLayoutManager(this.context)
        homeFollowersPost.layoutManager = linearLayoutManager
        homeFollowersPost.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        val adapterPosts =
            HomePostAdapter(response.body()!!.payload)
        homeFollowersPost.adapter = adapterPosts
        return homeFollowersPost
    }
}