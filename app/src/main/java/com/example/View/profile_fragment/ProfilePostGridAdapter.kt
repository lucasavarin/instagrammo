package com.example.view.profile_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.ProfilePostBean
import com.example.login.R

class ProfilePostGridAdapter(private val dataList : List<ProfilePostBean>): RecyclerView.Adapter<ProfilePostGridHolder>(){

        private lateinit var context : Context
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostGridHolder {
            context = parent.context
            val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_profile, parent,false)
            return ProfilePostGridHolder(inflatedView)
        }

        override fun getItemCount(): Int = dataList.size

        override fun onBindViewHolder(holder: ProfilePostGridHolder, position: Int) {
            holder.bindImgProfile(dataList.get(position))

        }

}