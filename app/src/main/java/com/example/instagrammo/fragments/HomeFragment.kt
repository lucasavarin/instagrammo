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
import com.example.instagrammo.beans.business.Followers
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.beans.response.FollowersWrapperREST
import com.example.instagrammo.beans.response.PostsWrapperResponseREST
import com.example.instagrammo.retrofit.RetrofitController
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {

    var followers: MutableList<Followers> = ArrayList()
    var posts: MutableList<Post> = ArrayList()

    private lateinit var linearLayoutManagerFollowers: LinearLayoutManager
    private lateinit var linearLayoutManagerPosts: LinearLayoutManager

    companion object{
        fun makeInstance():HomeFragment {
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
        callFollowers.enqueue(object: Callback<FollowersWrapperREST> {
            override fun onResponse(call: Call<FollowersWrapperREST>, response: Response<FollowersWrapperREST>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        for(follower in body.payload) {
                            followers.add(Followers.createBusinessBean(follower))
                        }
                        homeFollowerListLayout.adapter = FollowerListRecyclerAdapter(followers)
                        homeFollowerListLayout.adapter?.notifyDataSetChanged()
                    }else{
                        Toast.makeText(activity, "Nessun follower trovato", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FollowersWrapperREST>, t: Throwable) {
                Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }
        })

        val callPosts = RetrofitController.getClient.getPosts()

        callPosts.enqueue(object: Callback<PostsWrapperResponseREST> {
            override fun onResponse(call: Call<PostsWrapperResponseREST>, response: Response<PostsWrapperResponseREST>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        for(index in body.payload.size-1 downTo 0) {
                            posts.add(Post.createBusinessBean(body.payload[index]))
                        }
                        homePostListLayout.adapter = PostsListRecyclerAdapter(posts)
                        homePostListLayout.adapter?.notifyDataSetChanged()
                    }else{
                        Toast.makeText(activity, "Nessun post trovato", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PostsWrapperResponseREST>, t: Throwable) {
                Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }
        })
    }
}