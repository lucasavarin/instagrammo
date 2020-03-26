package com.example.view.add_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bean.buissnes.AddPostResponseBean
import com.example.bean.rest.request.CreatePostBeanRequest
import com.example.bean.rest.response.CreatePostBeanResponse
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import com.example.util.retrofit.Session
import com.example.util.utilities_project
import com.example.view.home_fragment.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_layout_thirt_fragment.*
import kotlinx.android.synthetic.main.add_layout_thirt_fragment.view.*
import kotlinx.android.synthetic.main.custom_view_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddThirdFragment(private val addBeanSessionFragment: AddPostResponseBean) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootestView : View = inflater.inflate(R.layout.add_layout_thirt_fragment, container, false)

            Picasso.get().load(addBeanSessionFragment.url)
            .transform(CircleTransform()).into(rootestView.imageConfermaPost)

        activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )

        return rootestView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.setOnClickListener { utilities_project.deleteFragment(AddThirdFragment(addBeanSessionFragment), activity!!) }
        confButton.setOnClickListener { createPost() }

    }

    private fun createPost() {

        ClientInterceptor.getUser.createPost(
            CreatePostBeanRequest(
                Session.profileId,
                editDesc.text.toString(),
                addBeanSessionFragment.download_url
            )
        ).enqueue(object : Callback<CreatePostBeanResponse>{
            override fun onFailure(call: Call<CreatePostBeanResponse>, t: Throwable) {
                Toast.makeText(context, "C'è stato un errore nella Creazione del Post", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<CreatePostBeanResponse>,
                response: Response<CreatePostBeanResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.result){
                        Toast.makeText(context, "Creazione andata a buon fine.", Toast.LENGTH_LONG).show()
                        utilities_project.deleteFragment(AddThirdFragment(addBeanSessionFragment), activity!!)
                        utilities_project.deleteFragment(AddSecondFragment(addBeanSessionFragment), activity!!)
                    }
                }
            }

        })



    }

}