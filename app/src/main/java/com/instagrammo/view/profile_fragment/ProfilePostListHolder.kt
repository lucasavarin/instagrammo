package com.instagrammo.view.profile_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.ProfilePostBean


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile.view.*
import kotlinx.android.synthetic.main.item_profile.view.oraPost
import kotlinx.android.synthetic.main.item_profile.view.titlePost


class ProfilePostHolder(itemView: View)  : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView


    fun bindImgTitleAndHourProfile(profile: ProfilePostBean){
        Picasso.get().load(profile.picture).resize(1200,500).centerCrop().into(view.imgProfile)
        view.titlePost.setText(profile.title)
        view.oraPost.setText(profile.uploadTime)
    }


    override fun onClick(v: View?) {

    }


}