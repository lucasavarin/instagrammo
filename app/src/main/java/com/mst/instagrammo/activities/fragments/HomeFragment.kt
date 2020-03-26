package com.mst.instagrammo.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mst.instagrammo.R
import com.mst.instagrammo.adapters.HomePostsRecyclerAdapter
import com.mst.instagrammo.adapters.StoriesRecyclerAdapter
import com.mst.instagrammo.api.ApiClient
import com.mst.instagrammo.model.HomePostsResponse
import com.mst.instagrammo.model.beans.Story
import com.mst.instagrammo.model.StoriesResponse
import com.mst.instagrammo.model.beans.HomePost
import com.mst.instagrammo.utilities.Session
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    var stories: MutableList<Story> = ArrayList()
    var homeposts: MutableList<HomePost> = ArrayList()

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
        getPosts(homeposts)

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
                        val adapter = StoriesRecyclerAdapter(stories)
                        homeStoriesLayout.adapter = adapter
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(activity, "NO stories", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(activity, "not successful", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun getPosts(homeposts: MutableList<HomePost>) {
        val call = ApiClient.getClient.getHomePosts()

        call.enqueue(object : Callback<HomePostsResponse> {
            override fun onFailure(call: Call<HomePostsResponse>, t: Throwable) {
                Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<HomePostsResponse>,
                responseHome: Response<HomePostsResponse>
            ) {
                if (responseHome.isSuccessful) {
                    val body = responseHome.body()!!
                    if (body.result) {
                        Toast.makeText(activity, "its ok", Toast.LENGTH_LONG).show()
                        for (homepost in body.payload) {
                            homeposts.add(
                                HomePost(
                                    homepost.profileId,
                                    homepost.postId,
                                    homepost.title,
                                    homepost.picture,
                                    homepost.uploadTime,
                                    homepost.profile
                                )
                            )
                        }
                        val adapter = HomePostsRecyclerAdapter(homeposts)
                        homePostsLayout.adapter = adapter
                        adapter.notifyDataSetChanged()
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
