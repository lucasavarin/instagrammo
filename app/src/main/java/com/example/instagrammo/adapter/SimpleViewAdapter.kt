package com.example.instagrammo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.ProfilePost

class SimpleViewAdapter (private val data : List<ProfilePost>): RecyclerView.Adapter<SimpleViewHolder>() {
    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.profile_grid_layout, parent, false)
        return SimpleViewHolder(inflatedView)
    }

    override fun getItemCount() : Int = data.size

    override fun onBindViewHolder(p0: SimpleViewHolder, p1: Int) {
        p0.bindPost(data[p1])
    }
}
