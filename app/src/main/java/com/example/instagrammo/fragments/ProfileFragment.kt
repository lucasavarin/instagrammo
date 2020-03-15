package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.adapters.PostGridRecyclerAdapter
import com.example.instagrammo.adapters.PostsListRecyclerAdapter
import com.example.instagrammo.adapters.TabAdapter
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.beans.business.Profile
import com.example.instagrammo.beans.business.ProfilePost
import com.example.instagrammo.beans.response.ProfilePostResponseWrapperREST
import com.example.instagrammo.beans.response.ProfileWrapperResponseREST
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.CircleTransform
import com.example.instagrammo.util.Session
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment: Fragment(){

    private lateinit var adapter:TabAdapter

    private var profile: Profile? = null
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
        adapter = TabAdapter(childFragmentManager)
        performCall()
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
                        Picasso.get().load(profile?.picture).transform(CircleTransform()).into(profile_pic)
                        amici_number.text = profile?.followersNumber
                        post_number.text = profile?.postsNumber
                        name.text = profile?.name
                        description.text = profile?.description
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
                        val gridFragment = GridProfileFragment.makeInstance()

                        val listFragment = ListProfileFragment.makeInstance()

                        val postsToAdapter: MutableList<Post> = ArrayList()
                        for(post in posts){
                            postsToAdapter.add(Post(
                                profile?.profileId,
                                post.postId,
                                post.title,
                                post.picture,
                                post.uploadTime,
                                profile
                            ))
                        }

                        listFragment.posts = postsToAdapter
                        gridFragment.posts = posts

                        listFragment.adapter = PostsListRecyclerAdapter(postsToAdapter)
                        gridFragment.adapter = PostGridRecyclerAdapter(posts)

                        listFragment.adapter.notifyDataSetChanged()
                        gridFragment.adapter.notifyDataSetChanged()

                        adapter.addFragment(gridFragment)
                        adapter.addFragment(listFragment)

                        viewPager.adapter = adapter
                        navigation.setupWithViewPager(viewPager)

                        navigation.getTabAt(0)?.setIcon(R.drawable.ic_view_module)
                        navigation.getTabAt(1)?.setIcon(R.drawable.ic_view_stream_black_24dp)
                    }
                } else{
                    Toast.makeText(activity, "Profilo non trovato", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}