package com.example.instagrammo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.HomePayloadPostBean

class HomePAdapter(private val postList: List<HomePayloadPostBean>) : RecyclerView.Adapter<HomePHolder>() {

    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePHolder {
        context = parent.context

        val View = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return HomePHolder(View)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(container: HomePHolder, position: Int) {
        container.assemblePost(postList[position])
    }
}