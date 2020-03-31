package com.instagrammo.view.profile_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.ProfilePostBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfilePostGridHolder(itemView: View)  : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView


    fun bindImgProfile(profile: ProfilePostBean){
        Picasso.get().load(profile.picture).resize(550,550).centerCrop().into(view.imagProfileGrid)
    }


    override fun onClick(v: View?) {

    }


}