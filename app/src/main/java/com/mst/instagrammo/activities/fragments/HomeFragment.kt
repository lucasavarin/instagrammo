package com.mst.instagrammo.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mst.instagrammo.R
import com.mst.instagrammo.adapters.StoriesRecyclerAdapter
import com.mst.instagrammo.api.ApiClient
import com.mst.instagrammo.model.StoriesRequest
import com.mst.instagrammo.model.beans.Story
import com.mst.instagrammo.model.StoriesResponse
import com.mst.instagrammo.utilities.Session
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    var stories: MutableList<Story> = ArrayList()
//    var posts: List<Post> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        getStories(stories)
//        getPosts(posts)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getStories(stories)
        val layoutManagerStories = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        homeStoriesLayout.layoutManager = layoutManagerStories
        val adapterS = StoriesRecyclerAdapter(stories)
        homeStoriesLayout.adapter = adapterS

//        val layoutManagerPosts = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        homePostsLayout.layoutManager = layoutManagerPosts
//        val adapterP = StoriesRecyclerAdapter(posts)
//        homeStoriesLayout.adapter = adapterP

    }

    fun getStories(stories: MutableList<Story>) {
        val call = ApiClient.getClient.getStoriesList()

        call.enqueue(object : Callback<StoriesResponse> {
            override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
                Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<StoriesResponse>,
                response: Response<StoriesResponse>
            ) {
                if (response.isSuccessful) {
                    var body = response.body()!!
                    if (body.result) {
                        Toast.makeText(activity, "its ok", Toast.LENGTH_LONG).show()
                        for (story in body.payload) {
                            stories.add(
                                Story(//"5", "dishdsh", "dhsudhs",  "https://i.picsum.photos/id/813/400/400.jpg"
                                    story.id,
                                    story.name,
                                    story.description,
                                    story.picture
                                )
                            )
                        }
                    } else {
                        Toast.makeText(activity, "niente stories", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(activity, "is not successful", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

//    fun getPosts(posts: ArrayList<Post>) {
//        val call = ApiClient.getClient.getPosts(Session.profileId)
//
//        call.enqueue(object : Callback<StoriesResponse> {
//            override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
//                Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
//            }
//
//            override fun onResponse(
//                call: Call<PostsResponse>,
//                response: Response<PostsResponse>
//            ) {
//                if (response.isSuccessful) {
//                    var body = response.body()!!
//                    if (body.result) {
//                        Toast.makeText(activity, "its ok", Toast.LENGTH_LONG).show()
//                        for (post in body.payload) {
//                            posts.add(
//                                Post(
//                                    post.id,
//                                    post.name,
//                                    post.description,
//                                    post.description
//                                )
//                            )
//                        }
//                    } else {
//                        Toast.makeText(activity, "niente stories", Toast.LENGTH_LONG).show()
//                    }
//                } else {
//                    Toast.makeText(activity, "is not successful", Toast.LENGTH_LONG).show()
//                }
//            }
//        })
//    }
}
