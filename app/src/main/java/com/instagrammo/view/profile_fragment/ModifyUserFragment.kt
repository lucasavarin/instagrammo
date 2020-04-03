package com.instagrammo.view.profile_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.instagrammo.bean.buissnes.ProfileModifyBean
import com.instagrammo.bean.buissnes.ProfileUpdateResponse
import com.example.login.R
import com.instagrammo.bean.buissnes.HomeProfilePostBean
import com.instagrammo.util.retrofit.ClientInterceptor
import com.instagrammo.util.utilities_project
import com.instagrammo.view.home_fragment.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_view_layout.*
import kotlinx.android.synthetic.main.modify_profile_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI


class ModifyUserFragment private constructor(private val profileBean : HomeProfilePostBean) : Fragment() {

    companion object {

        fun newInstance(profileBean : HomeProfilePostBean): ModifyUserFragment {
            return ModifyUserFragment(profileBean)
        }
    }


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.modify_profile_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataProfile()

        activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )

        checkImg.setOnClickListener {

            if (editImg.text.toString() != ""){
                val pathImage = "https://i.picsum.photos/id/${editImg.text.toString()}/450/450.jpg"
                Picasso.get().load(pathImage).transform(CircleTransform()).into(profileImg)
            }
        }

        backButton.setOnClickListener { utilities_project.deleteFragment(ModifyUserFragment(profileBean),activity!!) }

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
                        utilities_project.deleteFragment(ModifyUserFragment(profileBean), activity!!)
                    }
                })

        }
    }

    private fun getDataProfile() {
        modifyName.setText(profileBean.name)
        description.setText(profileBean.description)
        Picasso.get().load(profileBean.picture).transform(CircleTransform())
            .into(profileImg)
        editImg.setText(getIdParam(profileBean.picture))
    }

    private fun getIdParam(path: String): String? {
        val uri = URI(path)
        val pathImage: String = uri.path
        val pathFiltered = pathImage.substringAfter("id/")

        return pathFiltered.substringBefore("/4")
    }

    private fun createBeanUpdate(): ProfileModifyBean {
        val profileModifyBean = ProfileModifyBean(
            profileBean.profileId,
            modifyName.text.toString(),
            description.text.toString(),
            profileBean.picture
        )

        if (editImg.text.toString() != "") {
            profileModifyBean.picture =
                "https://i.picsum.photos/id/${editImg.text.toString()}/450/450.jpg"
        }

        return profileModifyBean
    }

}





