package com.instagrammo.view.home_fragment

import android.content.ComponentCallbacks
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.HomeUserResponseBean
import com.example.login.R
import kotlinx.android.synthetic.main.item_home_follower_story.view.*

class HomeFollowerStoryAdapter (private val dataList : List<HomeUserResponseBean>) : RecyclerView.Adapter<HomeFollowerStoryHolder>(){

    private lateinit var context : Context
    private var callBackProfile : ((HomeUserResponseBean) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFollowerStoryHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_horizontal_view, parent,false)
        return HomeFollowerStoryHolder(inflatedView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: HomeFollowerStoryHolder, position: Int) {
        holder.bindFollower(dataList.get(position))

        if(holder.itemView.userPostProfile != null){
            holder.itemView.userPostProfile.setOnClickListener {
                callBackProfile?.invoke(dataList[position])
            }
        }

    }

    fun callBackProfile(callbackOnHolder : (HomeUserResponseBean) -> Unit){
        this.callBackProfile = callbackOnHolder
    }



}