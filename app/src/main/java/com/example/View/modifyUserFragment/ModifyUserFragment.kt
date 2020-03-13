package com.example.View.modifyUserFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bean.buissnes.HomeWrapperResponse
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        ClientInterceptor.getUser.getFollowers().enqueue(object : Callback<HomeWrapperResponse> {
            override fun onFailure(call: Call<HomeWrapperResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<HomeWrapperResponse>,
                response: Response<HomeWrapperResponse>
            ) {
                getImgProfile(response)
            }
        })


        return inflater.inflate(R.layout.modify_profile_layout,container,false)
    }

    private fun getImgProfile(response: Response<HomeWrapperResponse>){
        response.body()!!.payload
    }
}