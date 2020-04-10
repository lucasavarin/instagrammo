package com.example.instagrammo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.business.Payload

class Adapter(private val myDataset: Array<Payload>) :
    RecyclerView.Adapter<MyViewHolder>() {

    private var func : ((Payload) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val lf = LayoutInflater.from(parent.context).inflate(R.layout.item_follower_view_layout,parent,false)
        return MyViewHolder(lf)
    }

    override fun getItemCount(): Int = myDataset.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.fillData(item)
        holder.v.setOnClickListener{
            this.func?.invoke(myDataset[position])
        }
    }

    fun setFunc(f: ((Payload) -> Unit)){
        this.func = f
    }

}



