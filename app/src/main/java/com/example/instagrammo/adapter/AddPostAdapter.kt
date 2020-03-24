package com.example.instagrammo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.AddResponseBeanApplicativo
import com.example.instagrammo.model.ProfilePost

class AddPostAdapter (private val data : List<AddResponseBeanApplicativo>): RecyclerView.Adapter<AddPostsHolder>(){

    var func : ((AddResponseBeanApplicativo) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPostsHolder {
      val v = LayoutInflater.from(parent.context).inflate(R.layout.add_post_item_layout,parent,false)
        return AddPostsHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AddPostsHolder, position: Int) {
       holder.bindView(data[position],func!!)
    }
}