package com.example.view.home_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.HomePayloadPostBean
import com.example.login.R

class HomeFollowerPostAdapter(private val dataList : List<HomePayloadPostBean>): RecyclerView.Adapter<HomeFollowerPostHolder>(){

    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFollowerPostHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_home_follower_story, parent,false)
        return HomeFollowerPostHolder(inflatedView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeFollowerPostHolder, position: Int) {
        holder.bindFollowerPost(dataList.get(position))
    }


}