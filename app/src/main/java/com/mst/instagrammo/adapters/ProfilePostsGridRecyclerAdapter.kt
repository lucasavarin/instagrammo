package com.mst.instagrammo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.R
import com.mst.instagrammo.model.beans.ProfilePost

class ProfilePostsGridRecyclerAdapter(val profileposts: List<ProfilePost>) : RecyclerView.Adapter<ProfilePostsGridHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostsGridHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.layout_posts_items_grid, parent, false)
        return ProfilePostsGridHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return profileposts.size
    }

    override fun onBindViewHolder(holder: ProfilePostsGridHolder, position: Int) {
        holder.bind(profileposts[position])
    }
}