package com.example.instagrammo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.Payload
import model.StoriesResponse

class Adapter(private val myDataset: Array<Payload>) :
    RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val lf = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view_layout,parent,false)
        return MyViewHolder(lf)
    }

    override fun getItemCount(): Int = myDataset.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.fillData(item)
    }

}



