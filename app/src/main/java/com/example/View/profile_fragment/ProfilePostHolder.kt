package com.example.view.profile_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.ProfileImgWrapper
import com.example.bean.buissnes.ProfilePostBean
import com.example.bean.buissnes.ProfilesWrapper


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile.view.*


class ProfilePostHolder(itemView: View)  : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView


    fun bindImgProfile(profile: ProfilePostBean){
        Picasso.get().load(profile.picture).resize(1000,500).centerCrop().into(view.imgProfile)
    }


    override fun onClick(v: View?) {

    }


}