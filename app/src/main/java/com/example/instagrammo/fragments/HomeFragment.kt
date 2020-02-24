package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.adapters.FollowerListRecyclerAdapter
import com.example.instagrammo.adapters.PostsListRecyclerAdapter
import com.example.instagrammo.beans.response.Followers
import com.example.instagrammo.beans.response.FollowersWrapper
import com.example.instagrammo.beans.response.Post
import com.example.instagrammo.beans.response.PostsWrapper
import com.example.instagrammo.retrofit.RetrofitController
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {

    var followers: List<Followers> = ArrayList()
    var posts: List<Post> = ArrayList()

    private lateinit var linearLayoutManagerFollowers: LinearLayoutManager
    private lateinit var linearLayoutManagerPosts: LinearLayoutManager

    companion object{
        fun makeInstance():Fragment {
            return HomeFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadHome()

        linearLayoutManagerFollowers = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        linearLayoutManagerPosts = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        homeFollowerListLayout.layoutManager = linearLayoutManagerFollowers
        homePostListLayout.layoutManager = linearLayoutManagerPosts

    }

    private fun loadHome(){
        val callFollowers = RetrofitController.getClient.getFollowers()
        callFollowers.enqueue(object: Callback<FollowersWrapper> {
            override fun onResponse(call: Call<FollowersWrapper>, response: Response<FollowersWrapper>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        followers = body.payload
                        homeFollowerListLayout.adapter = FollowerListRecyclerAdapter(followers)
                        homeFollowerListLayout.adapter?.notifyDataSetChanged()
                    }else{
                        Toast.makeText(activity, "Nessun follower trovato", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FollowersWrapper>, t: Throwable) {
                Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }
        })

        val callPosts = RetrofitController.getClient.getPosts()

        callPosts.enqueue(object: Callback<PostsWrapper> {
            override fun onResponse(call: Call<PostsWrapper>, response: Response<PostsWrapper>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        posts = body.payload
                        homePostListLayout.adapter = PostsListRecyclerAdapter(posts)
                        homePostListLayout.adapter?.notifyDataSetChanged()
                    }else{
                        Toast.makeText(activity, "Nessun post trovato", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PostsWrapper>, t: Throwable) {
                Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }
        })
    }
}