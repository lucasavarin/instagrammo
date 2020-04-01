package com.example.instagrammo.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.ProfilePostsBean

class ProfileGridAdapter(private val posts : List<ProfilePostsBean>) : RecyclerView.Adapter<ProfileGridHolder>() {
    lateinit var context : Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileGridHolder {
        context = parent.context

        val lf = LayoutInflater.from(context).inflate(R.layout.add_post_item_layout, parent, false)
        return ProfileGridHolder(lf)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ProfileGridHolder, position: Int) {
        holder.bindPosts(posts[position])
    }
}