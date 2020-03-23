package com.example.view.profile_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.bean.buissnes.ProfileImgWrapper
import com.example.bean.buissnes.ProfilePostBean
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import kotlinx.android.synthetic.main.profile_list_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileGridFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.profile_list_layout, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ClientInterceptor.getUser.getPosts("5","6").enqueue(object : Callback<ProfileImgWrapper> {

            override fun onFailure(call: Call<ProfileImgWrapper>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProfileImgWrapper>,
                response: Response<ProfileImgWrapper>
            ){
                if (response.isSuccessful){
                    if(!response.body()?.payloadProfile?.isEmpty()!!) {

                        putImg(response.body()!!.payloadProfile)
                        Log.d("risposta", response!!.body().toString())
                    }

                }

            }

        })
    }
    private fun putImg(response: List<ProfilePostBean>) : RecyclerView {
        var gridLayoutManager: LayoutManager? = null
             gridLayoutManager = GridLayoutManager(this.context,3, GridLayoutManager.HORIZONTAL, false)


        profileRecycleView.layoutManager = gridLayoutManager
        val adapterFollowers =
            ProfilePostAdapter(response)
        profileRecycleView.adapter = adapterFollowers

        return profileRecycleView
    }
}