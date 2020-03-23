package com.example.instagrammo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.ProfilePost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_view_layout.view.*

class DetailedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var post: ProfilePost? = null

    fun bindPost(followerPost : ProfilePost){
        this.post = followerPost
        if (followerPost.picture.isNotEmpty()) {
            Picasso.get().load(followerPost.picture).into(view.list_item)
        }else{
            itemView.list_item.setImageResource(R.drawable.ic_account_circle_black_24dp)
        }
    }
        override fun onClick(v: View?) {
        }

}