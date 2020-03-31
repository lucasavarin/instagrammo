package com.instagrammo.view.profile_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.ProfilePostBean
import com.example.login.R


class ProfilePostListAdapter(private val dataList : List<ProfilePostBean>): RecyclerView.Adapter<ProfilePostHolder>(){

    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_profile, parent,false)
        return ProfilePostHolder(inflatedView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ProfilePostHolder, position: Int) {
        holder.bindImgTitleAndHourProfile(dataList.get(position))

    }
}