package com.example.instagrammo.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.ProfilePostsBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_post_item_layout.view.*

class ProfileGridHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private var view : View = itemView

    fun bindPosts(profile : ProfilePostsBean){
        Picasso.get().load(profile.picture).fit().into(view.add_post)
    }
}