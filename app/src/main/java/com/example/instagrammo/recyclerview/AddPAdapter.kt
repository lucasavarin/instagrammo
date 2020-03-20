package com.example.instagrammo.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.AddPostResponse

class AddPAdapter(private val postData : List<AddPostResponse>) :
    RecyclerView.Adapter<AddPHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPHolder {
        val lf = LayoutInflater.from(parent.context).inflate(R.layout.add_post_item_layout,parent,false)
        return AddPHolder(lf)
    }

    override fun getItemCount(): Int = postData.size

    override fun onBindViewHolder(holder: AddPHolder, position: Int) {
        val item = postData[position]
        holder.fillGrid(item)
    }

}