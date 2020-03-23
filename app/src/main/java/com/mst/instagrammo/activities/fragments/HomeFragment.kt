package com.mst.instagrammo.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mst.instagrammo.R
import com.mst.instagrammo.adapters.PostsRecyclerAdapter
import com.mst.instagrammo.adapters.StoriesRecyclerAdapter
import com.mst.instagrammo.api.ApiClient
import com.mst.instagrammo.model.PostsResponse
import com.mst.instagrammo.model.beans.Story
import com.mst.instagrammo.model.StoriesResponse
import com.mst.instagrammo.model.beans.Post
import com.mst.instagrammo.utilities.Session
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    var stories: MutableList<Story> = ArrayList()
    var posts: MutableList<Post> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getStories(stories)
        getPosts(posts)

        //mock stories
//        stories.add(Story("5", "dishdsh", "dhsudhs",  "https://i.picsum.photos/id/813/400/400.jpg"))
//        stories.add(Story("5", "dishdsh", "dhsudhs",  "https://i.picsum.photos/id/813/400/400.jpg"))
//        stories.add(Story("5", "dishdsh", "dhsudhs",  "https://i.picsum.photos/id/813/400/400.jpg"))
//        stories.add(Story("5", "dishdsh", "dhsudhs",  "https://i.picsum.photos/id/813/400/400.jpg"))

        val layoutManagerStories = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeStoriesLayout.layoutManager = layoutManagerStories

        val layoutManagerPosts = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        homePostsLayout.layoutManager = layoutManagerPosts
    }

    private fun getStories(stories: MutableList<Story>) {
        val call = ApiClient.getClient.getStories(Session.profileId)

        call.enqueue(object : Callback<StoriesResponse> {
            override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
                Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<StoriesResponse>,
                response: Response<StoriesResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    if (body.result) {
//                        Toast.makeText(activity, "its ok", Toast.LENGTH_LONG).show()
                        for (story in body.payload) {
                            stories.add(
                                Story(
                                    story.id,
                                    story.name,
                                    story.description,
                                    story.picture
                                )
                            )
                        }
                        val adapterS = StoriesRecyclerAdapter(stories)
                        homeStoriesLayout.adapter = adapterS
                        adapterS.notifyDataSetChanged()
                    } else {
                        Toast.makeText(activity, "NO stories", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(activity, "not successful", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun getPosts(posts: MutableList<Post>) {
        val call = ApiClient.getClient.getPosts()

        call.enqueue(object : Callback<PostsResponse> {
            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<PostsResponse>,
                response: Response<PostsResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    if (body.result) {
                        Toast.makeText(activity, "its ok", Toast.LENGTH_LONG).show()
                        for (post in body.payload) {
                            posts.add(
                                Post(
                                    post.profileId,
                                    post.postId,
                                    post.title,
                                    post.picture,
                                    post.uploadTime,
                                    post.profile
                                )
                            )
                        }
                        val adapterP = PostsRecyclerAdapter(posts)
                        homePostsLayout.adapter = adapterP
                        adapterP.notifyDataSetChanged()
                    } else {
                        Toast.makeText(activity, "NO posts", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(activity, "not successful", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
