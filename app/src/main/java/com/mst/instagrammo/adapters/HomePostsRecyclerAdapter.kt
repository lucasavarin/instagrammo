package com.mst.instagrammo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.R
import com.mst.instagrammo.model.beans.HomePost

class HomePostsRecyclerAdapter(val homeposts: List<HomePost>) : RecyclerView.Adapter<HomePostsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostsHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.layout_home_posts_items, parent, false)
        return HomePostsHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return homeposts.size
    }

    override fun onBindViewHolder(holder: HomePostsHolder, position: Int) {
        holder.bind(homeposts.get(position))
    }
}