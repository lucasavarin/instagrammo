package com.mst.instagrammo.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.model.beans.ProfilePost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_posts_items_grid.view.*
import kotlinx.android.synthetic.main.layout_posts_items_list.view.*

class ProfilePostsListHolder (v: View): RecyclerView.ViewHolder(v) {
    private var view: View = v
    var profilepost: ProfilePost? = null

    fun bind(profilepost: ProfilePost){
        this.profilepost = profilepost
        Picasso.get().load(profilepost.picture).into(view.post_img_items)
        view.post_date.text = profilepost.uploadTime
        view.post_title.text = profilepost.title
    }
}