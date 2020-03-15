package com.example.instagrammo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.Post


class HomeFollowerPostAdapter(private val dataList: List<Post>): RecyclerView.Adapter<HomeFollowerPostHolder>(){

    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFollowerPostHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.post_layout_home, parent,false)
        return HomeFollowerPostHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeFollowerPostHolder, position: Int) {
        holder.bindFollowerPost(dataList[position])
    }


}