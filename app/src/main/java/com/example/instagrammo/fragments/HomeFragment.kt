package com.example.instagrammo.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.activities.MainActivity
import com.example.instagrammo.adapters.FollowerListRecyclerAdapter
import com.example.instagrammo.adapters.PostsListRecyclerAdapter
import com.example.instagrammo.applicationUtils.db
import com.example.instagrammo.beans.business.Follower
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.beans.business.Profile
import com.example.instagrammo.beans.response.FollowersWrapperREST
import com.example.instagrammo.beans.response.PostsWrapperResponseREST
import com.example.instagrammo.database.DatabaseHelper
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.isNetworkAvailable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.post_layout_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {

    private lateinit var activity:Activity
    var followers: MutableList<Follower> = ArrayList()
    var posts: MutableList<Post> = ArrayList()

    val postAdapter = PostsListRecyclerAdapter(arrayListOf())
    val followersAdapter = FollowerListRecyclerAdapter(arrayListOf())

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
        activity = getActivity()!! as com.example.instagrammo.activities.MainActivity
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Tenere premuto un post per mettere like", Toast.LENGTH_LONG).show()
        followersAdapter.followers = followers
        homeFollowerListLayout.adapter = followersAdapter
        postAdapter.posts = posts
        homePostListLayout.adapter = postAdapter
        postAdapter.setOnItemLongClickListener {
            it.like = !(it.like != null && it.like!!)
            db.likePost(it, it.like)
        }
        postAdapter.setOnLikeChangeListener {
            it.like = !(it.like != null && it.like!!)
            db.likePost(it, it.like)
        }
        if(isNetworkAvailable(activity)){
            downloadHome()
        }
        else{
            loadDb()
        }

        linearLayoutManagerFollowers = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        linearLayoutManagerPosts = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        homeFollowerListLayout.layoutManager = linearLayoutManagerFollowers
        homePostListLayout.layoutManager = linearLayoutManagerPosts

    }

    private fun loadDb(){
        posts.addAll(db.getPostList(null))
        val profiles = db.getProfileList(null)
        for(profile in profiles){
            followers.add(Follower(
                profile.profileId,
                profile.name,
                profile.description,
                profile.picture
            ))
        }

        homeFollowerListLayout.adapter?.notifyDataSetChanged()

        homePostListLayout.adapter?.notifyDataSetChanged()

    }


    private fun downloadHome(){
        val callFollowers = RetrofitController.getClient.getFollowers()
        callFollowers.enqueue(object: Callback<FollowersWrapperREST> {
            override fun onResponse(call: Call<FollowersWrapperREST>, response: Response<FollowersWrapperREST>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        for(follower in body.payload) {
                            val profile= Follower.createBusinessBean(follower)
                            followers.add(profile)
                            db.insertProfile(Profile(
                                profile.id,
                                profile.name,
                                profile.description,
                                profile.picture,
                                null,
                                null
                            ))
                        }
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
                            val post = Post.createBusinessBean(body.payload[index])
                            posts.add(post)
                            db.insertPost(post)
                        }
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