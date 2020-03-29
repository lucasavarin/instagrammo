package com.example.instagrammo.secondary_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.util.AddPostData
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.AddPostResponse
import com.example.instagrammo.util.addFragment
import com.example.instagrammo.util.deleteFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_header_view.*
import kotlinx.android.synthetic.main.fragment_add_post_detail.*
import kotlinx.android.synthetic.main.fragment_add_post_detail.view.*

class AddPostDetailFragment(private val addBean : AddPostResponse) : Fragment() {

    companion object{
        fun makeInstance(bean : AddPostResponse) : Fragment{
            return AddPostDetailFragment(bean)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_add_post_detail, container, false)
        Picasso.get().load(addBean.url).into(view.img_detail)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_btn.setOnClickListener {
            deleteFragment(AddPostDetailFragment(addBean), activity!!)
        }
        next_fragment.setOnClickListener{
            addFragment(AddFinalFragment(addBean), activity!!)
        }

    }

}