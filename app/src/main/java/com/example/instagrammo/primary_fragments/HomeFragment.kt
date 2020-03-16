package com.example.instagrammo.primary_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.recyclerview.HomePAdapter
import com.example.instagrammo.R
import com.example.instagrammo.data_class.ResponseStories
import com.example.instagrammo.recyclerview.StoriesAdapter
import com.example.instagrammo.beans.response.HomeWrapperPostBean
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.retrofit.Session
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
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

        Client.getClient.getPosts().enqueue(object : Callback<HomeWrapperPostBean>{
            override fun onFailure(call : Call<HomeWrapperPostBean>, t : Throwable){
//                Toast.makeText(this, "error", Toast.LENGTH_SHORT)
            }
            override fun onResponse(
                call: Call<HomeWrapperPostBean>, response: Response<HomeWrapperPostBean>
            ){
                addPost(response)
            }
        })
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        storiesV.layoutManager = layoutManager

        val retrofit = Client.getClient
        retrofit.getStoriesList(Session.profileId.toString()).enqueue(object :
            Callback<ResponseStories> {
            override fun onFailure(call: Call<ResponseStories>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseStories>,
                response: Response<ResponseStories>
            ) {
               Log.d("response", response.body()!!.payload.toString())
                view.storiesV.adapter =
                    StoriesAdapter(response.body()!!.payload)
            }
        })

    }

    companion object{
        fun makeInstance():Fragment {
            return HomeFragment()
        }
    }

    private fun addPost(response: Response<HomeWrapperPostBean>) : RecyclerView{
        val linearLayoutManager = LinearLayoutManager(this.context)
        homePost.layoutManager = linearLayoutManager
        homePost.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        val adapterPosts =
            HomePAdapter(response.body()!!.payload)
        homePost.adapter = adapterPosts
        return homePost
    }
}