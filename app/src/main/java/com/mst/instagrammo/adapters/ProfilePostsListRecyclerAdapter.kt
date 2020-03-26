package com.mst.instagrammo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.R
import com.mst.instagrammo.model.beans.ProfilePost

class ProfilePostsListRecyclerAdapter(val profileposts: List<ProfilePost>) : RecyclerView.Adapter<ProfilePostsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostsHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.layout_fragment_profile_list, parent, false)
        return ProfilePostsHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return profileposts.size
    }

    override fun onBindViewHolder(holder: ProfilePostsHolder, position: Int) {
//        holder.bind(profileposts.get(position))
    }
}