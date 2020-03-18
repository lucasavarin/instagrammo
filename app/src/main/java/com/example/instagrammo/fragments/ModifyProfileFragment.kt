package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.Profile
import com.example.instagrammo.beans.response.ProfileWrapperResponseREST
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.CircleTransform
import com.example.instagrammo.util.Session
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_modifyprofile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.profile_pic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyProfileFragment: Fragment(){
    private var profile: Profile? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_modifyprofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modifyCall()
        backButton.setOnClickListener {
            val fragmentManager: FragmentManager = activity !!.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val fragment = ProfileFragment()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    private fun modifyCall(){
        val callProfile = RetrofitController.getClient.getProfileSingle(Session.profileId)
        callProfile.enqueue(object: Callback<ProfileWrapperResponseREST> {
            override fun onFailure(call: Call<ProfileWrapperResponseREST>, t: Throwable) {
                Toast.makeText(activity, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ProfileWrapperResponseREST>,
                response: Response<ProfileWrapperResponseREST>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    if (body.result) {
                        profile = Profile.createBusinessBean(body.payload[0])
                        Picasso.get().load(profile?.picture).transform(CircleTransform())
                            .into(profile_pic)
                    } else {
                        Toast.makeText(activity, "Profilo non trovato", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Profilo non trovato", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}