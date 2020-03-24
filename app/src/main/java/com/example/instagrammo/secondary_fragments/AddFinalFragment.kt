package com.example.instagrammo.secondary_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.instagrammo.AddPostData
import com.example.instagrammo.R
import com.example.instagrammo.beans.request.CreatePostRequestBean
import com.example.instagrammo.beans.response.CreatePostResponseBean
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.retrofit.Session
import com.example.instagrammo.util.CircleTrasformation
import com.example.instagrammo.util.deleteFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_header_view.*
import kotlinx.android.synthetic.main.fragment_add_post_final_fragment.*
import kotlinx.android.synthetic.main.fragment_add_post_final_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFinalFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_add_post_final_fragment, container, false)
        Picasso.get().load(AddPostData.url[AddPostData.postPosition]).transform(CircleTrasformation()).into(view.round_add_photo)
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_btn.setOnClickListener {
            deleteFragment(AddFinalFragment(), activity!!)
        }

        post_button.setOnClickListener{createPost()}
    }

    private fun createPost(){
        Client.getClient.addPost(
            CreatePostRequestBean(
                Session.profileId, desc_edit.text.toString(), AddPostData.createUrl[AddPostData.postPosition]
            )
        ).enqueue(object : Callback<CreatePostResponseBean>{
            override fun onFailure(call: Call<CreatePostResponseBean>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<CreatePostResponseBean>,
                response: Response<CreatePostResponseBean>
            ) {
                if (response.isSuccessful){
                    if (response.body()!!.result){
                        deleteFragment(AddFinalFragment(), activity!!)

                        deleteFragment(AddPostDetailFragment(), activity!!)
                    }
                }
            }

        })
    }
}