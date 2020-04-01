package com.example.instagrammo.secondary_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.ProfilePostImgWrapperBean
import com.example.instagrammo.beans.response.ProfilePostsBean
import com.example.instagrammo.recyclerview.ProfileGridAdapter
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.retrofit.Session
import kotlinx.android.synthetic.main.profile_list_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GridLayoutFragment(private val profileId : String) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.profile_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Client.getClient.getPostsProfile(this.profileId).enqueue(object : Callback<ProfilePostImgWrapperBean>{
            override fun onFailure(call: Call<ProfilePostImgWrapperBean>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ProfilePostImgWrapperBean>,
                response: Response<ProfilePostImgWrapperBean>
            ) {
                fillImg(response.body()!!.payloadProfilePosts)
            }

        })


    }

    private fun fillImg(response: List<ProfilePostsBean>) : RecyclerView{
        val gLayoutM : RecyclerView.LayoutManager
        gLayoutM = GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL, false)

        postRecycle.layoutManager = gLayoutM
        val adapter = ProfileGridAdapter(response)
        postRecycle.adapter = adapter

        return postRecycle
    }


    companion object{
        fun makeInstance(profileId: String = Session.profileId):GridLayoutFragment {
            return GridLayoutFragment(profileId)
        }
    }
}