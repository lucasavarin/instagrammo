package com.instagrammo.view.home_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.HomePayloadPostBean
import com.example.login.R


class HomeFollowerPostAdapter(private val dataList : List<HomePayloadPostBean>): RecyclerView.Adapter<HomeFollowerPostHolder>(){

    private var callback: (() -> Unit)? = null
    private var callBackProfile : ((HomePayloadPostBean) -> Unit)? = null
    private lateinit var context : Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFollowerPostHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_home_follower_story, parent,false)
        return HomeFollowerPostHolder(inflatedView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeFollowerPostHolder, position: Int) {
        holder.bindFollowerPost(dataList.get(position))

        holder.itemView.setOnClickListener {
            callBackProfile?.invoke(dataList[position])
        }

        if(position >= itemCount -5){
            callback?.invoke()
        }
    }

    fun setOnLastItemsCallback(callback: () -> Unit){
        this.callback = callback
    }

    fun callBackProfile(callbackOnHolder : (HomePayloadPostBean) -> Unit){
        this.callBackProfile = callbackOnHolder
    }


}


