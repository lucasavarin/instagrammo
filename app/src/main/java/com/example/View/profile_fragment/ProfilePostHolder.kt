package com.example.view.profile_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.HomeProfilePostBean


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile.view.*


class ProfilePostHolder(itemView: View)  : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView

    fun bindImgProfile(profile: HomeProfilePostBean){
        Picasso.get().load(profile.picture).into(view.imgProfile)
    }


    override fun onClick(v: View?) {

    }


}