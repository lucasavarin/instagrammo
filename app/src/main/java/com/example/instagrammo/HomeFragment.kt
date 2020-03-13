package com.example.instagrammo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.home_fragment_layout.*
import model.HomeWrapperPostBean
import kotlinx.android.synthetic.main.home_fragment_layout.view.*
import model.Payload
import model.Session
import model.StoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    companion object {

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()

            return fragment
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        RetrofitController.getClient.getFollowerPost().enqueue(object : Callback<HomeWrapperPostBean>{
            override fun onFailure(call: Call<HomeWrapperPostBean>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<HomeWrapperPostBean>,
                response: Response<HomeWrapperPostBean>
            ) {
                createPost(response)
            }
        })
        return inflater.inflate(R.layout.home_fragment_layout, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.progressBar2.visibility = View.VISIBLE
        val lManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false )
        rView.layoutManager = lManager
        val retrofit = RetrofitController.getClient
        retrofit.getStoriesList(Session.profileId.toString()).enqueue(object :Callback<StoriesResponse>{
            override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<StoriesResponse>,
                response: Response<StoriesResponse>
            ) {
                view.progressBar2.visibility = View.GONE


               // val ad = Adapter( response!!.body()!!.payload)
                Log.d("response",response!!.body()!!.payload.toString())
                view.rView.adapter = Adapter( response!!.body()!!.payload)


            }
        })

    }

    private fun createPost(response : Response<HomeWrapperPostBean>) : RecyclerView {
        val linearLayoutManager = LinearLayoutManager(this.context)
        HomeFollowerPosts.layoutManager = linearLayoutManager
        HomeFollowerPosts.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        val adapterFollowerPost =
            HomeFollowerPostAdapter(response.body()!!.payload)
        HomeFollowerPosts.adapter = adapterFollowerPost
        return HomeFollowerPosts
    }

}