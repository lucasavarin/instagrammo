package thushyanthan.scott.javalynx.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import thushyanthan.scott.javalynx.instagrammo.ApiClient
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs.prefs
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.adapter.HomeAdapter
import thushyanthan.scott.javalynx.instagrammo.adapter.ProfileGridAdapter
import thushyanthan.scott.javalynx.instagrammo.fragments.secondaryFragments.EditProfileFragment
import thushyanthan.scott.javalynx.instagrammo.util.rest.*


class ProfileFragment: Fragment() {

    var profile: List<PayloadProfile> = ArrayList()
    var posts: List<PostPayload> = ArrayList()
    private lateinit var linearLayoutManagerPost: LinearLayoutManager
    private lateinit var gridLayoutManagerPost: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)



    override fun onActivityCreated(savedInstanceState: Bundle?) {


        super.onActivityCreated(savedInstanceState)
        getSingleProfile()
        editButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,
                EditProfileFragment.makeInstance(profile[0].profileId, profile[0].name, profile[0].description,profile[0].picture))?.commit()
        }

        getProfilePost()

        linearLayoutManagerPost = LinearLayoutManager(activity,RecyclerView.VERTICAL, false )
        gridLayoutManagerPost = GridLayoutManager(context, 3)

        profilePostListLayout.layoutManager = linearLayoutManagerPost
        profilePostGridLayout.layoutManager = gridLayoutManagerPost

    }



    fun getSingleProfile() {
        val singleProfileCall = ApiClient.getClient.getSingleProfile(prefs.profiloUtente)
        singleProfileCall.enqueue(object: Callback<ProfileResponse>{
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Toast.makeText(activity, "Error GetSingleProfile1",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if(response.isSuccessful){
                    val resultBody = response.body()!!
                    if(resultBody.result){
                        profile = resultBody.payload
                    }
                }else{
                    Toast.makeText(activity, "Error GetSingleProfile2",Toast.LENGTH_SHORT).show()
                }


            }
        })

    }

    fun getProfilePost() {
        val postProfile = ApiClient.getClient.requestPosts()

        postProfile.enqueue(object : Callback<PostsResponse>{
            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                Toast.makeText(activity, "Error1Posts", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if(response.isSuccessful){
                    val resultBody = response.body()!!
                    if(resultBody.result){
                        posts = resultBody.payload
                        profilePostListLayout.adapter = HomeAdapter(posts, context!!)
                        profilePostListLayout.adapter?.notifyDataSetChanged()
                        profilePostGridLayout.adapter = ProfileGridAdapter(posts, context!!)
                        profilePostGridLayout.adapter?.notifyDataSetChanged()
                    }
                }else{
                    Toast.makeText(activity, "Error2Posts", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }




}