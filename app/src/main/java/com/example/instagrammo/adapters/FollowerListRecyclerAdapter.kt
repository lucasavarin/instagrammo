package com.example.instagrammo.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.Followers
import com.example.instagrammo.util.inflate

class FollowerListRecyclerAdapter(private val followers: List<Followers>): RecyclerView.Adapter<FollowersListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersListHolder {
        val inflatedView = parent.inflate(R.layout.followers_layout_item, false)
        return FollowersListHolder(inflatedView)
    }

    override fun getItemCount()= followers.size

    override fun onBindViewHolder(holder: FollowersListHolder, position: Int) {
        holder.bindFollower(followers[position])
    }

}