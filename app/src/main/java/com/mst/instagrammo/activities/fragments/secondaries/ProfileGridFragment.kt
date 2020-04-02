package com.mst.instagrammo.activities.fragments.secondaries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mst.instagrammo.R
import com.mst.instagrammo.adapters.ProfilePostsGridRecyclerAdapter
import com.mst.instagrammo.adapters.ProfilePostsListRecyclerAdapter
import com.mst.instagrammo.api.ApiClient
import com.mst.instagrammo.model.ProfilePostsResponse
import com.mst.instagrammo.model.beans.ProfilePost
import com.mst.instagrammo.utilities.Session
import kotlinx.android.synthetic.main.fragment_profile_post_grid.*
import kotlinx.android.synthetic.main.fragment_profile_post_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileGridFragment : Fragment() {
    var posts: MutableList<ProfilePost> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.fragment_profile_post_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPosts(posts)

        val layoutManagerPosts = GridLayoutManager(context, 3)
        profilePostsGridLayout.layoutManager = layoutManagerPosts
    }

    private fun getPosts(posts: MutableList<ProfilePost>) {
        val call = ApiClient.getClient.getProfilePosts(Session.profileId)

        call.enqueue(object : Callback<ProfilePostsResponse> {
            override fun onFailure(call: Call<ProfilePostsResponse>, t: Throwable) {
                Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ProfilePostsResponse>,
                responseHome: Response<ProfilePostsResponse>
            ) {
                if (responseHome.isSuccessful) {
                    val body = responseHome.body()!!
                    if (body.result) {
//                        Toast.makeText(activity, "its ok", Toast.LENGTH_LONG).show()
                        for (post in body.payload) {
                            posts.add(
                                ProfilePost(
                                    post.profileId,
                                    post.title,
                                    post.picture,
                                    post.uploadTime
                                )
                            )
                        }
                        val adapter = ProfilePostsGridRecyclerAdapter(posts)
                        profilePostsGridLayout.adapter = adapter
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