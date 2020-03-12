package com.example.instagrammo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_fragment_layout.*
import model.Payload
import model.Session
import model.StoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    companion object {

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()

            return fragment
        }

       lateinit var payloads : Array<Payload>

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.home_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar2.visibility = View.VISIBLE
        val retrofit = RetrofitController.getClient
        retrofit.getStoriesList(Session.profileId.toString()).enqueue(object :Callback<StoriesResponse>{
            override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            }

            override fun onResponse(
                call: Call<StoriesResponse>,
                response: Response<StoriesResponse>
            ) {
                progressBar2.visibility = View.GONE
                payloads = response!!.body()!!.payload
                val lManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
                rView.layoutManager = lManager
                rView.adapter = Adapter(payloads,context!!)
            }
        })

    }


}