package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagrammo.R
import com.example.instagrammo.adapters.FollowerListRecyclerAdapter
import com.example.instagrammo.adapters.PostsListRecyclerAdapter
import com.example.instagrammo.applicationUtils.db
import com.example.instagrammo.beans.business.Follower
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.beans.business.Profile
import com.example.instagrammo.beans.response.FollowersWrapperREST
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.isNetworkAvailable
import com.example.instagrammo.util.replaceFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment: Fragment() {

    var followers = arrayListOf<Follower>()
    val adapter = PostsListRecyclerAdapter(arrayListOf())

    companion object{
        fun makeInstance():SearchFragment {
            return SearchFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnItemClickListener {
            (context as AppCompatActivity).replaceFragment(ProfileFragment.makeInstance(it.profileId?.toInt()!!), R.id.container, "profile")
        }
        search_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        search_list.adapter = adapter
        if(isNetworkAvailable(context)){
            download()
        }
        else{
            loadDb()
        }
    }

    fun populateFields(){
        for(follower in followers) {
            adapter.posts.add(
                Post(
                    follower.id,
                    "",
                    "",
                    follower.picture,
                    "",
                    Profile(
                        follower.id,
                        follower.name,
                        follower.description,
                        follower.picture,
                        null,
                        null
                    )

                )
            )
        }
        adapter.notifyDataSetChanged()
    }

    fun loadDb(){
        val list = db.getProfileList(null)
        for(bean in list){
            followers.add(Follower.getFollowerFromProfile(bean))
        }
        adapter.notifyDataSetChanged()
        populateFields()
    }


    fun download(){
        val callFollowers = RetrofitController.getClient.getFollowers()
        callFollowers.enqueue(object: Callback<FollowersWrapperREST> {
            override fun onResponse(call: Call<FollowersWrapperREST>, response: Response<FollowersWrapperREST>) {
                if(response.isSuccessful){
                    val body = response.body()!!
                    if(body.result){
                        for(follower in body.payload) {
                            followers.add(Follower.createBusinessBean(follower))
                            db.insertProfile(
                                Profile(
                                    follower.id,
                                    follower.name,
                                    follower.description,
                                    follower.picture,
                                    null,
                                    null
                                )
                            )
                        }
                        populateFields()
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
    }
}