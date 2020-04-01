package com.example.instagrammo.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.ProfilePostsBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_list_item_layout.view.*

class ProfileListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var view : View = itemView

    fun bindPosts(profile : ProfilePostsBean){
        Picasso.get().load(profile.picture).into(view.user_post_list)
        view.post_hour_list.text = profile.uploadTime
        view.post_title_list.text = profile.title
    }
}