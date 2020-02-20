package com.example.instagrammo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.StoriesResponse

class Adapter(private val myDataset: Array<StoriesResponse>) :
    RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val lf = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view_layout,parent,false)
        return MyViewHolder(lf)
    }

    override fun getItemCount(): Int = myDataset.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

}



