package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.ProfilePost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_grid_view_layout.view.*
import kotlinx.android.synthetic.main.item_follower_view_layout.view.*
import kotlinx.android.synthetic.main.item_list_view_layout.view.*
import kotlinx.android.synthetic.main.profile_grid_layout.view.*
import kotlinx.android.synthetic.main.profile_post_layout.view.*
import kotlinx.android.synthetic.main.profilo_fragment_layout.view.*

class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view : View = itemView
    private var post : ProfilePost? = null

    fun bindPost(followerPost : ProfilePost){
        this.post = followerPost
        if (followerPost.picture.isNotEmpty()) {
            Picasso.get().load(followerPost.picture).into(view.grid_item)
        }else{
            itemView.list_item.setImageResource(R.drawable.ic_account_circle_black_24dp)
        }
    }
    override fun onClick(v: View?) {
    }
}