package com.example.instagrammo.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.HomePayloadPostBean

class HomePAdapter(private val postList: List<HomePayloadPostBean>) : RecyclerView.Adapter<HomePHolder>() {

    private lateinit var context : Context
    private var userProfileCallBack : ((HomePayloadPostBean) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePHolder {
        context = parent.context

        val View = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return HomePHolder(View)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: HomePHolder, position: Int) {
        holder.assemblePost(postList[position])

        holder.itemView.setOnClickListener{
            userProfileCallBack?.invoke(postList[position])
        }

    }

    fun userProfileCallBack(callback : (HomePayloadPostBean) -> Unit){
        this.userProfileCallBack = callback
    }
}