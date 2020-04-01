package com.example.instagrammo.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.data_class.ProfiloStories
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.stories_item_layout.view.*

class StoriesAdapter(private val myDataset: Array<ProfiloStories>) :
    RecyclerView.Adapter<ViewHolderStories>() {


    private var userProfileCallBack : ((ProfiloStories) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStories {
        val lf = LayoutInflater.from(parent.context).inflate(R.layout.stories_item_layout,parent,false)
        return ViewHolderStories(lf)
    }

    override fun getItemCount(): Int = myDataset.size


    override fun onBindViewHolder(holder: ViewHolderStories, position: Int) {
        val item = myDataset[position]
        holder.fillStories(item)

        holder.itemView.recyclerViewFollowers.setOnClickListener {
            userProfileCallBack?.invoke(myDataset[position])
        }
    }

    fun userProfileCallBack(callBack : (ProfiloStories) ->Unit){
        this.userProfileCallBack = callBack
    }
}