package com.example.instagrammo.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.util.inflate

class PostsListRecyclerAdapter(private val posts: List<Post>): RecyclerView.Adapter<PostsListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsListHolder {
        val inflatedView = parent.inflate(R.layout.post_layout_item, false)
        return PostsListHolder(inflatedView)
    }

    override fun getItemCount()= posts.size

    override fun onBindViewHolder(holder: PostsListHolder, position: Int) {
        holder.bindPost(posts[position])
    }
}