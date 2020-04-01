package com.example.instagrammo.primary_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.recyclerview.HomePAdapter
import com.example.instagrammo.R
import com.example.instagrammo.data_class.ResponseStories
import com.example.instagrammo.recyclerview.StoriesAdapter
import com.example.instagrammo.beans.response.HomeWrapperPostBean
import com.example.instagrammo.data_class.DbPost
import com.example.instagrammo.data_class.ProfiloStories
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.retrofit.Session
import com.example.instagrammo.shared_prefs.db
import com.example.instagrammo.util.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Client.getClient.getPosts().enqueue(object : Callback<HomeWrapperPostBean>{
            override fun onFailure(call : Call<HomeWrapperPostBean>, t : Throwable){
                addPostFromDb()
            }
            override fun onResponse(
                call: Call<HomeWrapperPostBean>, response: Response<HomeWrapperPostBean>
            ){
                addPost(response)
                val savedPost = arrayListOf<DbPost>()
                response.body()!!.payload.forEach{
                    val newPost = DbPost(it.profileId, it.postId, it.uploadTime, it.title, it.picture)
                    savedPost.add(newPost)
                }
                db.savePost(savedPost)
            }
        })
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        storiesV.layoutManager = layoutManager

        val retrofit = Client.getClient
        retrofit.getStoriesList(Session.profileId).enqueue(object :
            Callback<ResponseStories> {
            override fun onFailure(call: Call<ResponseStories>, t: Throwable) {
                view.storiesV.adapter =
                    StoriesAdapter(db.getFollowers().toTypedArray())
            }

            override fun onResponse(
                call: Call<ResponseStories>,
                response: Response<ResponseStories>
            ) {

               Log.d("response", response.body()!!.payload.toString())

                val adapterStories =
                    StoriesAdapter(response.body()!!.payload)

                adapterStories.userProfileCallBack {
                    val userFragment : Fragment = UserFragment.makeInstance(it.id)
                    (context as AppCompatActivity).replaceFragment(userFragment, R.id.container)
                }

                view.storiesV.adapter = adapterStories

                val savedFollower = arrayListOf<ProfiloStories>()
                response.body()!!.payload.forEach {
                    val newFollower = ProfiloStories(it.id, it.description, it.picture, it.name)
                    savedFollower.add(newFollower)
                }
                db.saveFollowers(savedFollower.toTypedArray())
            }
        })

    }

    companion object{
        fun makeInstance():Fragment {
            return HomeFragment()
        }
    }

    private fun addPost(response: Response<HomeWrapperPostBean>) : RecyclerView{
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.stackFromEnd = true
        linearLayoutManager.reverseLayout = true
        homePost.layoutManager = linearLayoutManager
        homePost.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        val adapterPosts =
            HomePAdapter(response.body()!!.payload)
        adapterPosts.userProfileCallBack {
            val userFragment : Fragment = UserFragment.makeInstance(it.profileId)
            (context as AppCompatActivity).replaceFragment(userFragment, R.id.container)
        }
        homePost.adapter = adapterPosts
        return homePost
    }

    private fun addPostFromDb() : RecyclerView{
        val lLM = LinearLayoutManager(this.context)
        lLM.stackFromEnd = true
        lLM.reverseLayout = true
        homePost.layoutManager = lLM
        homePost.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        val adapter =
            HomePAdapter(db.getPosts())
        homePost.adapter = adapter
        return homePost
    }
}