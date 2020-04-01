package com.example.instagrammo.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.ProfilePostsBean

class ProfileListAdapter(private val posts : List<ProfilePostsBean>): RecyclerView.Adapter<ProfileListHolder>() {

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileListHolder {
        context = parent.context

        val lf = LayoutInflater.from(context).inflate(R.layout.post_list_item_layout, parent, false)
        return ProfileListHolder(lf)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ProfileListHolder, position: Int) {
        holder.bindPosts(posts[position])
    }
}