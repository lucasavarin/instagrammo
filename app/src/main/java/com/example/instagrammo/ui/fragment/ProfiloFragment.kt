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
import com.example.instagrammo.R
import com.example.instagrammo.adapter.ProfileAdapter
import com.example.instagrammo.model.*
import com.example.instagrammo.retrofit.RetrofitController
import kotlinx.android.synthetic.main.profile_post_layout.*
import kotlinx.android.synthetic.main.profilo_fragment_layout.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ProfiloFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var profile: Profile? = null
    private var description:String = ""
    private var nome:String =""
    private var imageUrl :String =""
    private var profileId :String =""
    private lateinit var posts: List<Post>

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
                createProfilePost(response)
                description = response.body()!!.payload[0].description
                nome = response.body()!!.payload[0].name
                imageUrl = response.body()!!.payload[0].picture
                profileId = response.body()!!.payload[0].profileId
            }
        })


        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.profilo_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridLayoutManager = GridLayoutManager(activity, 3)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
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

    }
    fun createProfilePost(response: Response<ProfileWrapperRest>) : RecyclerView{
        val linearLayoutManager = LinearLayoutManager(this.context)
        gridview.layoutManager = linearLayoutManager
        gridview.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        val adapterFollowerPost =
            ProfileAdapter(response.body()!!.payload)
        gridview.adapter = adapterFollowerPost
        return gridview

    }



}