package com.example.instagrammo.secondary_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.ProfileWrapperBean
import com.example.instagrammo.data_class.EditProfileBean
import com.example.instagrammo.data_class.SaveProfileBean
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.retrofit.Session
import com.example.instagrammo.util.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileFragment : Fragment() {

    lateinit var profileId : String
    lateinit var picture : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Client.getClient.getProfile(Session.profileId.toString()).enqueue(object : Callback<ProfileWrapperBean>{
            override fun onFailure(call: Call<ProfileWrapperBean>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProfileWrapperBean>,
                response: Response<ProfileWrapperBean>
            ) {
                fillData(response)
            }

        })
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_btn.setOnClickListener{
            val fragment = EditProfileFragment()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment)
            fragmentTransaction.commit()
            fragmentManager.popBackStack()}

        save_btn.setOnClickListener{
            Client.getClient.saveProfile(saveProfileData()).enqueue(object : Callback<SaveProfileBean>{
                override fun onFailure(call: Call<SaveProfileBean>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SaveProfileBean>,
                    response: Response<SaveProfileBean>
                ) {
                    Toast.makeText(context, "Successo", Toast.LENGTH_SHORT).show()
                    val fragment = EditProfileFragment()
                    val fragmentManager = activity!!.supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.remove(fragment)
                    fragmentTransaction.commit()
                    fragmentManager.popBackStack()
                }

            })
        }
    }

    fun fillData(response: Response<ProfileWrapperBean>){
        profile_name.setText(response.body()!!.payload[0].name)
        profile_description.setText(response.body()!!.payload[0].description)
        Picasso.get().load(response.body()!!.payload[0].picture).transform(CircleTrasformation()).into(img)
        profileId = response.body()!!.payload[0].profileId
        picture = response.body()!!.payload[0].picture
    }

    fun saveProfileData() : EditProfileBean{
        val editProfile = EditProfileBean(profileId, profile_name.text.toString(), profile_description.text.toString(), picture)
        if (img_edit.text.toString()!= ""){
            editProfile.picture = "https://i.picsum.photos/id/${img_edit.text.toString()}/450/450.jpg"
        }
        return editProfile
    }
}