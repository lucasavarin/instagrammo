package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.util.db
import com.example.instagrammo.R
import com.example.instagrammo.adapter.Adapter
import com.example.instagrammo.adapter.HomeFollowerPostAdapter
import com.example.instagrammo.model.business.PostDb
import com.example.instagrammo.model.business.Session
import com.example.instagrammo.model.business.StoriesResponse
import com.example.instagrammo.model.rest.response.HomeWrapperPostBean
import com.example.instagrammo.util.retrofit.RetrofitController
import kotlinx.android.synthetic.main.home_fragment_layout.*
import kotlinx.android.synthetic.main.home_fragment_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    companion object {

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        RetrofitController.getClient.getFollowerPost()
            .enqueue(object : Callback<HomeWrapperPostBean> {
                override fun onFailure(call: Call<HomeWrapperPostBean>, t: Throwable) {
                    val linearLayoutManager = LinearLayoutManager(context)
                    linearLayoutManager.stackFromEnd = true
                    linearLayoutManager.reverseLayout = true
                    HomeFollowerPosts.layoutManager = linearLayoutManager
                    HomeFollowerPosts.addItemDecoration(
                        DividerItemDecoration(
                            context,
                            LinearLayoutManager.VERTICAL
                        )
                    )
                    val adapterFollowerPost =
                        HomeFollowerPostAdapter(db.getPostsFromDb())
                    HomeFollowerPosts.adapter = adapterFollowerPost

                }

                override fun onResponse(
                    call: Call<HomeWrapperPostBean>,
                    response: Response<HomeWrapperPostBean>
                ) {
                    createPost(response)
                    val daSalvare = arrayListOf<PostDb>()
                    response!!.body()!!.payload.forEach {
                        val nuovo: PostDb =
                            PostDb(
                                it.profileId,
                                it.postId,
                                it.title,
                                it.uploadTime
                            )
                        daSalvare.add(nuovo)
                    }
                    db.writableDatabase.execSQL("DELETE FROM ${com.example.instagrammo.db.dbcontractclass.Contract.PostEntry.TABLE_NAME}")
                    db.savePostOnDb(daSalvare)

                }
            })
        return inflater.inflate(R.layout.home_fragment_layout, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.progressBar2.visibility = View.VISIBLE
        val lManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rView.layoutManager = lManager
        val retrofit = RetrofitController.getClient
        retrofit.getStoriesList(Session.profileId.toString())
            .enqueue(object : Callback<StoriesResponse> {
                override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
                    view.progressBar2.visibility = View.INVISIBLE
                    val lManager =
                        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                    rView.layoutManager = lManager
                    rView.adapter = Adapter(db.getFollowerFromDB())
                }

                override fun onResponse(
                    call: Call<StoriesResponse>,
                    response: Response<StoriesResponse>
                ) {
                    view.progressBar2.visibility = View.GONE
                    db.writableDatabase.execSQL("DELETE FROM ${com.example.instagrammo.db.dbcontractclass.Contract.FollowerEntry.TABLE_NAME}")
                    db.saveFollowerOnDb(response.body()!!.payload)
                    // val ad = Adapter( response!!.body()!!.payload)
                    Log.d("response", response!!.body()!!.payload.toString())
                    view.rView.adapter = Adapter(response!!.body()!!.payload)
                }
            })
    }

    private fun createPost(response: Response<HomeWrapperPostBean>): RecyclerView {
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.stackFromEnd = true
        linearLayoutManager.reverseLayout = true
        HomeFollowerPosts.layoutManager = linearLayoutManager
        HomeFollowerPosts.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        val adapterFollowerPost =
            HomeFollowerPostAdapter(response.body()!!.payload)
        HomeFollowerPosts.adapter = adapterFollowerPost
        return HomeFollowerPosts
    }
}