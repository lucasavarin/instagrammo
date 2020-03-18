package com.mst.instagrammo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.R
import com.mst.instagrammo.model.beans.Story

class StoriesRecyclerAdapter(val stories: List<Story>) : RecyclerView.Adapter<StoriesHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.layout_home_stories_items, parent, false)
        return StoriesHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: StoriesHolder, position: Int) {
        holder.bind(stories.get(position))
    }
}