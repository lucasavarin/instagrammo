package com.example.view.profile_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bean.buissnes.ProfileModifyBean
import com.example.bean.buissnes.ProfileUpdateResponse
import com.example.bean.buissnes.ProfileWrapperDataBean
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import com.example.view.home_fragment.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_view_layout.*
import kotlinx.android.synthetic.main.modify_profile_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI


class ModifyUserFragment : Fragment() {

    lateinit var profileId: String
    lateinit var profilePicture: String

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ClientInterceptor.getUser.getProfileData()
            .enqueue(object : Callback<ProfileWrapperDataBean> {
                override fun onFailure(call: Call<ProfileWrapperDataBean>, t: Throwable) {
                    Toast.makeText(context, "ErroreNellaChiamata", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ProfileWrapperDataBean>,
                    response: Response<ProfileWrapperDataBean>
                ) {
                    getDataProfile(response)
                    profileId = response.body()!!.payload[0].profileId
                    profilePicture = response.body()!!.payload[0].picture
                }

            })

        return inflater.inflate(R.layout.modify_profile_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkImg.setOnClickListener {

            if (editImg.text.toString() != ""){
                val pathImage = "https://i.picsum.photos/id/${editImg.text.toString()}/450/450.jpg"
                Picasso.get().load(pathImage).transform(CircleTransform()).into(profileImg)
            }
        }

        backButton.setOnClickListener {

            backProfileFragment(this@ModifyUserFragment)

        }

        saveButton.setOnClickListener {
            ClientInterceptor.getUser.updateProfileData(createBeanUpdate())
                .enqueue(object : Callback<ProfileUpdateResponse> {
                    override fun onFailure(call: Call<ProfileUpdateResponse>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "Impossibile effettuare l'update del profilo.",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<ProfileUpdateResponse>,
                        response: Response<ProfileUpdateResponse>
                    ) {
                        Toast.makeText(context, "Aggiornamento andato a buon fine.", Toast.LENGTH_LONG).show()
                        backProfileFragment(this@ModifyUserFragment)
                    }
                })

        }
    }

    private fun getDataProfile(response: Response<ProfileWrapperDataBean>) {
        modifyName.setText(response.body()!!.payload[0].name)
        description.setText(response.body()!!.payload[0].description)
        Picasso.get().load(response.body()!!.payload[0].picture).transform(CircleTransform())
            .into(profileImg)
        editImg.setText(getIdParam(response.body()!!.payload[0].picture))
    }

    private fun getIdParam(path: String): String? {
        val uri = URI(path)
        val pathImage: String = uri.path
        val pathFiltered = pathImage.substringAfter("id/")

        return pathFiltered.substringBefore("/4")
    }

    private fun createBeanUpdate(): ProfileModifyBean {
        val profileModifyBean = ProfileModifyBean(
            profileId,
            modifyName.text.toString(),
            description.text.toString(),
            profilePicture
        )

        if (editImg.text.toString() != "") {
            profileModifyBean.picture =
                "https://i.picsum.photos/id/${editImg.text.toString()}/450/450.jpg"
        }

        return profileModifyBean
    }

    private fun backProfileFragment(fragment : Fragment) {

        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
        fragmentManager.popBackStack()

    }

}





