package com.mst.instagrammo.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.mst.instagrammo.R
import com.mst.instagrammo.adapters.ProfilePagerAdapter
import com.mst.instagrammo.api.ApiClient
import com.mst.instagrammo.model.ProfileResponse
import com.mst.instagrammo.model.beans.Profile
import com.mst.instagrammo.utilities.CircleTransform
import com.mst.instagrammo.utilities.Session
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.layout_home_stories_items.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment(){
    var profile: MutableList<Profile> = ArrayList()

    private lateinit var profilePagerAdapter: ProfilePagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProfile(profile)

        profilePagerAdapter = ProfilePagerAdapter(childFragmentManager)
        viewPager = view.viewPagerPictures
        viewPager.adapter = profilePagerAdapter
    }

    private fun getProfile(profile: MutableList<Profile>) {
        val call = ApiClient.getClient.getProfile(Session.profileId)

        call.enqueue(object : Callback<ProfileResponse> {
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    if (body.result) {
//                        Toast.makeText(activity, "its ok", Toast.LENGTH_LONG).show()
                        for (prof in body.payload) {
                            profile.add(
                                Profile(
                                    prof.profileId,
                                    prof.name,
                                    prof.description,
                                    prof.picture,
                                    prof.followersNumber,
                                    prof.postsNumber
                                )
                            )
                        }
                        Picasso.get().load(profile[0].picture).transform(CircleTransform()).into(profile_propic)
                        profile_followers_number.text = profile[0].followersNumber
                        profile_posts_number.text = profile[0].postsNumber
                        profile_username.text = profile[0].name
                        profile_description.text = profile[0].description
                    } else {
                        Toast.makeText(activity, "NO stories", Toast.LENGTH_LONG).show()
                    }
                }else {
                    Toast.makeText(activity, "not successful", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
