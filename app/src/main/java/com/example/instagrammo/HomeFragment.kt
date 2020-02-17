package com.example.instagrammo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagrammo.beans.response.Followers
import com.example.instagrammo.beans.response.FollowersWrapper
import com.example.instagrammo.beans.response.Post
import com.example.instagrammo.beans.response.PostsWrapper
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.Session
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {

    var followers: List<Followers> = ArrayList()
    var posts: List<Post> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager

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
        inflater.inflate(R.layout.fragment_home, container)
        loadHome()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        linearLayoutManager = LinearLayoutManager(activity)
        homeFollowerListLayout.layoutManager = linearLayoutManager
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadHome(){
        val callFollowers = RetrofitController.getClient.getFollowers(Session.profileId, Session.token)
        callFollowers.enqueue(object: Callback<FollowersWrapper> {
            override fun onResponse(call: Call<FollowersWrapper>, response: Response<FollowersWrapper>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        followers = body.payload
                    }else{
                        Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FollowersWrapper>, t: Throwable) {
                Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }
        })

        val callPosts = RetrofitController.getClient.getPosts(Session.token)

        callPosts.enqueue(object: Callback<PostsWrapper> {
            override fun onResponse(call: Call<PostsWrapper>, response: Response<PostsWrapper>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        posts = body.payload
                    }else{
                        Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
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