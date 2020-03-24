package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.model.AddPostInfo
import com.example.instagrammo.model.AddPostResult
import com.example.instagrammo.model.AddResponseBeanApplicativo
import com.example.instagrammo.model.Session
import com.example.instagrammo.retrofit.RetrofitController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.confirm_add_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePost(val post : AddResponseBeanApplicativo) : Fragment() {

    companion object {

        fun newInstance(post : AddResponseBeanApplicativo): CreatePost {
            val fragment = CreatePost(post)

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val confView : View = inflater.inflate(R.layout.confirm_add_layout, container, false)
        return confView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(post.urlModificato).into(img_prof)
        val retrofit = RetrofitController.getClient
        val desc = descr.text.toString()
        conf_btn.setOnClickListener {
            retrofit.createPost(AddPostInfo(Session.profileId.toString(), desc, post.urlModificato))
                .enqueue(
                    object : Callback<AddPostResult> {
                        override fun onFailure(call: Call<AddPostResult>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<AddPostResult>,
                            response: Response<AddPostResult>
                        ) {
                            fragmentManager!!.beginTransaction().remove(this@CreatePost)
                                .replace(R.id.frame, AddFragment.newInstance()).commit()
                        }
                    }
                )
        }
        headercustom2.setOnBackClickListener {
            fragmentManager!!.beginTransaction().remove(this@CreatePost)
                .replace(R.id.frame, AddFragment.newInstance()).commit()
        }
    }

    }
