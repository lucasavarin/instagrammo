package com.instagrammo.view.profile_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.ProfilePostBean
import com.squareup.picasso.Callback


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile.view.*
import kotlinx.android.synthetic.main.item_profile.view.oraPost
import kotlinx.android.synthetic.main.item_profile.view.titlePost
import java.lang.Exception


class ProfilePostHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {

    private var view: View = itemView


    fun bindImgTitleAndHourProfile(profile: ProfilePostBean){
        view.progressProfileList.visibility = View.VISIBLE

        Picasso.get().load(profile.picture).resize(1200,500).centerCrop().into(view.imgProfile, object : Callback{
            override fun onSuccess() {
                view.progressProfileList.visibility = View.GONE

            }

            override fun onError(e: Exception?) { }

        })

        view.titlePost.text = profile.title
        view.oraPost.text = profile.uploadTime

    }

}