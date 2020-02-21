package com.example.HomeFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.squareup.picasso.Picasso

class RecycleViewVerticalAdapter (private val dataList : List<HomeUserResponseBean>) : RecyclerView.Adapter<RecycleViewVerticalHolder>(){

    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewVerticalHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_horizontal_view, parent,false)
        return RecycleViewVerticalHolder(inflatedView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecycleViewVerticalHolder, position: Int) {
        holder.bindFollower(dataList.get(position))
    }


}