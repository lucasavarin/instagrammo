package com.example.instagrammo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.HomePayloadPostBean

class HomePostAdapter(private val dList: List<HomePayloadPostBean>) : RecyclerView.Adapter<HomePostHolder>() {

    private lateinit var context : Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return HomePostHolder(inflatedView)
    }

    override fun getItemCount(): Int = dList.size

    override fun onBindViewHolder(holder: HomePostHolder, position: Int) {
        holder.bindFollower(dList[position])
    }
}