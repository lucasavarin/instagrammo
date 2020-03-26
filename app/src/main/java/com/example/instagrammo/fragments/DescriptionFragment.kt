package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.instagrammo.R
import com.example.instagrammo.beans.request.UpdatePostREST
import com.example.instagrammo.beans.response.PostPutResponseREST
import com.example.instagrammo.retrofit.RetrofitController
import com.example.instagrammo.util.CircleTransform
import com.example.instagrammo.util.DOWNLOAD_URL
import com.example.instagrammo.util.DOWNLOAD_URL_REFORMED
import com.example.instagrammo.util.Session
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_image_full_screen.*
import kotlinx.android.synthetic.main.fragment_modifyprofile.*
import kotlinx.android.synthetic.main.fragment_modifyprofile.backButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DescriptionFragment:Fragment() {

    lateinit var downloadUrl:String
    lateinit var downloadUrlReformed:String

    companion object{
        fun makeInstance():DescriptionFragment{
            return DescriptionFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            downloadUrl = arguments!!.getString(DOWNLOAD_URL, "")
            downloadUrlReformed = arguments!!.getString(DOWNLOAD_URL_REFORMED, "")
        }

        backDescription.setOnClickListener {
            val fragmentManager: FragmentManager = activity !!.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val fragment = AddFragment()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        confirm.setOnClickListener {

            val call = RetrofitController.getClient.updatePost(UpdatePostREST(Session.profileId.toString(), description.text.toString(), downloadUrl))

            call.enqueue(object:Callback<PostPutResponseREST>{
                override fun onFailure(call: Call<PostPutResponseREST>, t: Throwable) {
                    Toast.makeText(context, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<PostPutResponseREST>,
                    response: Response<PostPutResponseREST>
                ) {
                    if(response.isSuccessful){
                        if(response.body()!!.result){
                            Toast.makeText(context, "Post caricato con successo", Toast.LENGTH_SHORT).show()
                        }
                    } else{
                        Toast.makeText(context, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                    }
                }

            })

            val fragmentManager: FragmentManager = activity !!.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val fragment = AddFragment()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        Picasso.get().load(downloadUrl).transform(CircleTransform()).into(image_description)
    }
}