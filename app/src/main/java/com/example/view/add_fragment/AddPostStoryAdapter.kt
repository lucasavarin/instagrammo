package com.example.view.add_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.AddPostResponseBean
import com.example.login.R


class AddPostStoryAdapter(var dataList : List<AddPostResponseBean>) : RecyclerView.Adapter<AddPostStoryHolder>(){

    private lateinit var context : Context

    private var callback : (() -> Unit)? = null
    private var callBackOnHolderClickListener : ((AddPostResponseBean) -> Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AddPostStoryHolder {
        context = p0.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_recycle_view_add, p0,false)
        return AddPostStoryHolder(inflatedView)

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: AddPostStoryHolder, position: Int) {

        holder.bindPosts(dataList.get(position))

        holder.itemView.setOnClickListener {

            callBackOnHolderClickListener?.invoke(dataList[position])

        }

        if(position >= itemCount - 5){
          callback?.invoke()
        }

    }

    fun setOnAddPostScrollListener(callback : () -> Unit){
        this.callback = callback
    }

    fun callBackOnHolderClickListener(callbackOnHolder : (AddPostResponseBean) -> Unit){
        this.callBackOnHolderClickListener = callbackOnHolder
    }

}



