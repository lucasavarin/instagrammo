package com.mst.instagrammo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.R
import com.mst.instagrammo.model.beans.Post

class PostsRecyclerAdapter(val posts: List<Post>) : RecyclerView.Adapter<PostsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.layout_home_posts_items, parent, false)
        return PostsHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        holder.bind(posts.get(position))
    }
}