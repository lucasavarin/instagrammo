package com.example.instagrammo.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.beans.business.ProfilePost
import com.example.instagrammo.util.inflate

class PostGridRecyclerAdapter(private val posts: List<ProfilePost>): RecyclerView.Adapter<PostGridHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostGridHolder {
        val inflatedView = parent.inflate(R.layout.grid_layout_item, false)
        return PostGridHolder(inflatedView)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostGridHolder, position: Int) {
        holder.bindPost(posts[position])
    }
}