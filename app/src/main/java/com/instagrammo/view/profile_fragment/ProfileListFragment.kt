package com.instagrammo.view.profile_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.instagrammo.bean.buissnes.ProfileImgWrapper
import com.instagrammo.bean.buissnes.ProfilePostBean
import com.example.login.R
import com.instagrammo.util.retrofit.ClientInterceptor
import com.instagrammo.util.retrofit.Session
import kotlinx.android.synthetic.main.profile_list_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileListFragment private constructor(private val profileId : String) : Fragment() {

    companion object {

        fun newInstance(profileId : String = Session.profileId): ProfileListFragment {
            val profileListFragment = ProfileListFragment(profileId)
            return profileListFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_list_layout, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ClientInterceptor.getUser.getPosts(this.profileId).enqueue(object : Callback<ProfileImgWrapper> {

            override fun onFailure(call: Call<ProfileImgWrapper>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Impossibile caricare le immagini.",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<ProfileImgWrapper>,
                response: Response<ProfileImgWrapper>
            ){
                if (response.isSuccessful){
                    if(!response.body()?.payloadProfile?.isEmpty()!!) {

                        putImg(response.body()!!.payloadProfile)
                        Log.d("risposta", response.body().toString())
                    }

                }

            }

        })
    }
    private fun putImg(response: List<ProfilePostBean>) : RecyclerView {
        var gridLayoutManager: LayoutManager? = null
            gridLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            profileRecycleView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))


        profileRecycleView.layoutManager = gridLayoutManager
        val adapterFollowers =
            ProfilePostListAdapter(response)
        profileRecycleView.adapter = adapterFollowers

        return profileRecycleView
    }
}