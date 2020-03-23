package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.instagrammo.ApplicationContext
import com.example.instagrammo.R
import com.example.instagrammo.adapter.DetailedPostAdapter
import com.example.instagrammo.adapter.ProfileAdapter
import com.example.instagrammo.adapter.TabAdapter
import com.example.instagrammo.model.*
import com.example.instagrammo.picassotransformation.CircleTrasformation
import com.example.instagrammo.retrofit.RetrofitController
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile_post_layout.*
import kotlinx.android.synthetic.main.profilo_fragment_layout.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ProfiloFragment() : Fragment() {

    private var adapter: TabAdapter? = null
    val gridLayoutManager = GridLayoutManager(this.context,3, LinearLayoutManager.VERTICAL, false)
    val linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

    private var profile: Profile? = null
    private var description:String = ""
    private var nome:String =""
    private var imageUrl :String =""
    private var profileId :String =""

    companion object {

        fun newInstance(): ProfiloFragment {
            val fragment = ProfiloFragment()

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        RetrofitController.getClient.getProfile().enqueue(object : Callback<ProfileWrapperRest>{
            override fun onFailure(call: Call<ProfileWrapperRest>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<ProfileWrapperRest>,
                response: Response<ProfileWrapperRest>

            ) {
                description = response.body()!!.payload[0].description
                nome = response.body()!!.payload[0].name
                imageUrl = response.body()!!.payload[0].picture
                profileId = response.body()!!.payload[0].profileId
                fillDataUser(description,imageUrl,response!!.body()!!.payload[0].followersNumber,response!!.body()!!.payload[0].postsNumber)
            }
        })

        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.profilo_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TabAdapter(childFragmentManager)
        Posts.layoutManager = linearLayoutManager
        buttonProfilo.setOnClickListener { v ->
            val f =
                ModificaProfiloFragment.getIstance(
                    profileId,
                    nome,
                    description,
                    imageUrl
                )
            fragmentManager!!.beginTransaction().add(R.id.frame, f, "TAG").commit()
        }

        RetrofitController.getClient.getProfilePost().enqueue(object : Callback<ProfilePostBean>{
            override fun onFailure(call: Call<ProfilePostBean>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<ProfilePostBean>,
                response: Response<ProfilePostBean>
            ) {
                createPost(response)
            }
        })
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when(p0?.position){
                    0 -> Posts.layoutManager = gridLayoutManager
                    1 -> Posts.layoutManager = linearLayoutManager
                }
            }

        })
    }

    private fun createPost(response : Response<ProfilePostBean>)  {
        val adapterDetailedPost = DetailedPostAdapter(response.body()!!.payload)
        Posts.layoutManager = gridLayoutManager
        Posts.adapter = adapterDetailedPost
    }

    fun fillDataUser(desc:String,image:String,nAmici:String,nPosts:String){
        Picasso.get().load(image).resize(175,175).transform(CircleTrasformation()).into(Profile_photo)
        Post_number.text = nPosts
        Friend_number.text = nAmici
        Bio.text = desc
    }
}