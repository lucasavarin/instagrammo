package com.lynxspa.instagrammo.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lynxspa.instagrammo.model.AppDataBean
import kotlinx.android.synthetic.main.item_app_data.view.*

class CustomHolder(view:View): RecyclerView.ViewHolder(view) {

    fun bindBean(bean: AppDataBean){
        itemView.titolo.text = bean.titolo
        itemView.descrizione.text = bean.descrizione
    }
}