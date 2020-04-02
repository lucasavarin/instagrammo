package com.mst.instagrammo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.R
import com.mst.instagrammo.model.beans.ProfilePost

class ProfilePostsListRecyclerAdapter(val profileposts: List<ProfilePost>) : RecyclerView.Adapter<ProfilePostsListHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostsListHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.layout_posts_items_list, parent, false)
        return ProfilePostsListHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return profileposts.size
    }

    override fun onBindViewHolder(holder: ProfilePostsListHolder, position: Int) {
        holder.bind(profileposts[position])
    }
}