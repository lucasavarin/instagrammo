package com.mst.instagrammo.activities.fragments.secondaries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mst.instagrammo.R
import com.mst.instagrammo.adapters.HomePostsRecyclerAdapter
import com.mst.instagrammo.api.ApiClient
import com.mst.instagrammo.model.ProfilePostsResponse
import com.mst.instagrammo.model.beans.ProfilePost
import com.mst.instagrammo.utilities.Session
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileListFragment : Fragment() {
    var profileposts: MutableList<ProfilePost> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.layout_fragment_profile_list, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPosts(profileposts)
    }

    private fun getPosts(profileposts: MutableList<ProfilePost>) {
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
                        Toast.makeText(activity, "its ok", Toast.LENGTH_LONG).show()
                        for (profilepost in body.payload) {
                            profileposts.add(
                                ProfilePost(
                                    profilepost.profileId,
                                    profilepost.postId,
                                    profilepost.picture,
                                    profilepost.uploadTime
                                )
                            )
                        }
//                        val adapter = ProfilePostsRecyclerAdapter(profileposts)
//                        homePostsLayout.adapter = adapter
//                        adapter.notifyDataSetChanged()
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