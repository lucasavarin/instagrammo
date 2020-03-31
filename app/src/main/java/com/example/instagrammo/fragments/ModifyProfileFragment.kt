package com.example.instagrammo.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.instagrammo.R
import com.example.instagrammo.activities.MainActivity
import com.example.instagrammo.beans.business.Profile
import com.example.instagrammo.beans.request.ProfilePutREST
import com.example.instagrammo.beans.response.ProfilePutResponseREST
import com.example.instagrammo.beans.response.ProfileWrapperResponseREST
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.CircleTransform
import com.example.instagrammo.util.Session
import com.example.instagrammo.util.replaceFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_modifyprofile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.profile_pic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI


class ModifyProfileFragment private constructor(
    val picture: String,
    val profileId: String,
    val description: String,
    val nomeProfilo: String
): Fragment(){

    private var profile: Profile? = null

    companion object {
        fun makeInstance(picture: String,
                         profileId: String,
                         description: String,
                         nomeProfilo: String
        ): ModifyProfileFragment {
            return ModifyProfileFragment(
                picture,
                profileId,
                description,
                nomeProfilo
            )
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_modifyprofile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveButtonChangeStatus()
        Picasso.get().load(picture).transform(CircleTransform())
            .into(profile_pic)
        okButton.setOnClickListener {
            if (editTextPicture.text.toString() != ""){
                val pathImage = "https://i.picsum.photos/id/${editTextPicture.text.toString()}/450/450.jpg"
                Picasso.get().load(pathImage).transform(CircleTransform()).into(profile_pic)
            }
        }
        backButton.setOnClickListener {
            (activity as MainActivity).replaceFragment(ProfileFragment.makeInstance(), R.id.container, "")
        }

        saveButton.setOnClickListener {
            RetrofitController.getClient.updateProfile(updateProfile())
                .enqueue(object : Callback<ProfilePutResponseREST> {
                    override fun onFailure(call: Call<ProfilePutResponseREST>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "Impossibile effettuare l'update del profilo.",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<ProfilePutResponseREST>,
                        response: Response<ProfilePutResponseREST>
                    ) {
                        Toast.makeText(context, "Aggiornamento andato a buon fine.", Toast.LENGTH_LONG).show()
                            val fragmentManager = activity !!.supportFragmentManager
                            val fragmentTransaction = fragmentManager.beginTransaction()
                            val fragment = ModifyProfileFragment(picture, profileId, description, nomeProfilo)
                            fragmentTransaction.remove(fragment)
                            fragmentTransaction.commit()
                            fragmentManager.popBackStack()

                    }
                })
        }

    }
    /*private fun getDataProfile() {
        editTextNomeProfilo.setText(nomeProfilo)
        editTextDescrizione.setText(description)
        Picasso.get().load(picture).transform(CircleTransform())
            .into(profile_pic)
        editTextPicture.setText(getIdParam(picture))
    }*/

    /*private fun getIdParam(path: String): String? {
        val uri = URI(path)
        val pathImage: String = uri.path
        val pathFiltered = pathImage.substringAfter("id/")

        return pathFiltered.substringBefore("/4")
    }*/

    private fun updateProfile():ProfilePutREST{

        var profileModif = ProfilePutREST(
            profileId,
            editTextNomeProfilo.text.toString(),
            editTextDescrizione.text.toString(),
            picture
        )
        if (editTextPicture.text.toString() != "") {
            profileModif.picture =
                "https://i.picsum.photos/id/${editTextPicture.text.toString()}/450/450.jpg"
        }
        return profileModif
    }
    private fun saveButtonChangeStatus(){
        editTextNomeProfilo.addTextChangedListener(editor)
        editTextDescrizione.addTextChangedListener(editor)
    }
    val editor = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val nomeProfilo = editTextNomeProfilo.text.toString()
            val descrizione = editTextDescrizione.text.toString()
            saveButton.isEnabled = nomeProfilo.trim().isNotEmpty() && descrizione.trim().isNotEmpty()
        }
    }
}



