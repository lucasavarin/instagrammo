package com.lynxspa.instagrammo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.R
import com.lynxspa.instagrammo.model.AppDataBean


class CustomAdapter(var list: MutableList<AppDataBean> = arrayListOf()): RecyclerView.Adapter<CustomHolder>() {

    private var callBackLong : ((AppDataBean) -> Unit ) ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return CustomHolder(inflater.inflate(R.layout.item_app_data, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.bindBean(list[position])

        holder.itemView.setOnLongClickListener {
            callBackLong?.invoke(list[position])
            return@setOnLongClickListener true
        }
    }

    fun setOnItemLongClickListener(callBackLong : (AppDataBean) -> Unit ){
        this.callBackLong = callBackLong
    }
}