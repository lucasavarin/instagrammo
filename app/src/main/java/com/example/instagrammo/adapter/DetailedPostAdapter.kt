package com.example.instagrammo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.Post
import com.example.instagrammo.model.ProfilePost

class DetailedPostAdapter(private val data : List<ProfilePost>): RecyclerView.Adapter<DetailedViewHolder>() {

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedViewHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_list_view_layout, parent,false)
        return DetailedViewHolder(inflatedView)
    }

    override fun getItemCount() : Int = data.size

    override fun onBindViewHolder(p0: DetailedViewHolder, p1: Int) {
        p0.bindPost(data[p1])
    }

}