package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.adapters.PostGridRecyclerAdapter
import com.example.instagrammo.adapters.PostsListRecyclerAdapter
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.beans.business.Profile
import com.example.instagrammo.beans.business.ProfilePost
import com.example.instagrammo.beans.rest.ProfilePostResponseWrapperREST
import com.example.instagrammo.beans.rest.ProfileWrapperResponseREST
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.CircleTransform
import com.example.instagrammo.util.Session
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment: Fragment(){

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var profile: Profile
    private lateinit var posts: MutableList<ProfilePost>

    companion object{
        fun makeInstance():ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        posts = ArrayList<ProfilePost>()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutManager = GridLayoutManager(activity, 3)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        grid_list_recycler_view.layoutManager = gridLayoutManager

        performCall()

        navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId){
                R.id.grid -> {
                    grid_list_recycler_view.layoutManager = gridLayoutManager
                    grid_list_recycler_view.adapter = PostGridRecyclerAdapter(posts.toList())
                    true
                }
                R.id.list -> {
                    grid_list_recycler_view.layoutManager = linearLayoutManager
                    var postToAdapter:MutableList<Post> = ArrayList<Post>()
                    for(post in posts){
                        postToAdapter.add(
                            Post(
                            profile.profileId,
                            post.postId,
                            post.title,
                            post.picture,
                            post.uploadTime,
                            profile
                        ))
                    }
                    grid_list_recycler_view.adapter = PostsListRecyclerAdapter(postToAdapter.toList())
                    true
                }
                else -> false
            }
        }
    }

    private fun performCall(){
        val callProfile = RetrofitController.getClient.getProfileSingle(Session.profileId)

        callProfile.enqueue(object: Callback<ProfileWrapperResponseREST> {
            override fun onFailure(call: Call<ProfileWrapperResponseREST>, t: Throwable) {
                Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ProfileWrapperResponseREST>, response: Response<ProfileWrapperResponseREST>) {
                if(response.isSuccessful) {
                    val body = response.body()!!
                    if (body.result) {
                        profile = Profile.createBusinessBean(body.payload[0])
                        Picasso.get().load(profile.picture).transform(CircleTransform()).into(profile_pic)
                        amici_number.text = profile.followersNumber
                        post_number.text = profile.postsNumber
                        name.text = profile.name
                        description.text = profile.description
                    } else {
                        Toast.makeText(activity, "Profilo non trovato", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Profilo non trovato", Toast.LENGTH_SHORT).show()
                }
            }

        })

        val callPosts = RetrofitController.getClient.getProfilePosts(Session.profileId)

        callPosts.enqueue(object: Callback<ProfilePostResponseWrapperREST>{
            override fun onFailure(call: Call<ProfilePostResponseWrapperREST>, t: Throwable) {
                Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ProfilePostResponseWrapperREST>, response: Response<ProfilePostResponseWrapperREST>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        for(post in body.payload){
                            posts.add(ProfilePost.createBusinessBean(post))
                        }
                        grid_list_recycler_view.adapter = PostGridRecyclerAdapter(posts.toList())
                        grid_list_recycler_view.adapter?.notifyDataSetChanged()
                    }
                } else{
                    Toast.makeText(activity, "Profilo non trovato", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}