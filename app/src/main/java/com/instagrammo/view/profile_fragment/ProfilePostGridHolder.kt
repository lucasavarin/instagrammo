package com.instagrammo.view.profile_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.ProfilePostBean
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile.view.*
import kotlinx.android.synthetic.main.item_profile_grid.view.*
import java.lang.Exception

class ProfilePostGridHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {

    private var view: View = itemView

    fun bindImgProfile(profile: ProfilePostBean){
        view.progressProfileGrid.visibility = View.VISIBLE

        Picasso.get().load(profile.picture).into(view.imagProfileGrid, object : Callback{
            override fun onSuccess() {
                view.progressProfileGrid.visibility = View.GONE
            }

            override fun onError(e: Exception?) { }
        })
    }

}