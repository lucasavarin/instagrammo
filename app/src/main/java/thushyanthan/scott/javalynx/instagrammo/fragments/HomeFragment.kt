package thushyanthan.scott.javalynx.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.*
import thushyanthan.scott.javalynx.instagrammo.adapter.FollowerAdapter
import thushyanthan.scott.javalynx.instagrammo.adapter.HomeAdapter
import thushyanthan.scott.javalynx.instagrammo.util.rest.FollowerPayload
import thushyanthan.scott.javalynx.instagrammo.util.rest.FollowerResponse
import thushyanthan.scott.javalynx.instagrammo.util.rest.PostPayload
import thushyanthan.scott.javalynx.instagrammo.util.rest.PostsResponse
import thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs.prefs
import java.util.ArrayList

class HomeFragment: Fragment() {
    var posts: List<PostPayload> = ArrayList()
    var followers : List<FollowerPayload> = ArrayList()
    private lateinit var linearLayoutManagerFollowers: LinearLayoutManager
    private lateinit var linearLayoutManagerPosts: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getPosts()
        getFollowers()

        linearLayoutManagerPosts = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        linearLayoutManagerFollowers = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)

        homePostsListLayout.layoutManager = linearLayoutManagerPosts
        homeFollowersListLayout.layoutManager = linearLayoutManagerFollowers
    }

    fun getPosts(){
        val postsCall = ApiClient.getClient.requestPosts()

        postsCall.enqueue(object : Callback<PostsResponse>{
            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                Toast.makeText(activity, "Error1Posts", Toast.LENGTH_SHORT).show()
            }


            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if(response.isSuccessful){
                    val resultBody = response.body()!!
                    if(resultBody.result){
                        posts = resultBody.payload
                        homePostsListLayout.adapter =
                            HomeAdapter(
                                posts,
                                context!!
                            )
                        homePostsListLayout.adapter?.notifyDataSetChanged()
                    }
                }else
                    Toast.makeText(activity, "Error2Posts", Toast.LENGTH_SHORT).show()

            }
        })
    }

    fun getFollowers(){
        val followersCall = ApiClient.getClient.requestFollowers(prefs.profiloUtente)
        followersCall.enqueue(object : Callback<FollowerResponse>{
            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                Toast.makeText(activity, "Error1Followers", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<FollowerResponse>,
                response: Response<FollowerResponse>
            ) {
                if(response.isSuccessful){
                    val resultBody = response.body()!!
                    if(resultBody.result){
                        followers = resultBody.payload
                        homeFollowersListLayout.adapter =
                            FollowerAdapter(
                                followers,
                                context!!
                            )
                        homeFollowersListLayout.adapter?.notifyDataSetChanged()
                    }
                }else
                    Toast.makeText(activity, "Error2Followers", Toast.LENGTH_SHORT).show()
            }
        })
    }










}