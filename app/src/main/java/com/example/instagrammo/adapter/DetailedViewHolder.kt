package com.example.instagrammo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.Post
import com.example.instagrammo.picassotransformation.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_layout_home.view.*
import kotlinx.android.synthetic.main.profile_post_layout.*
import kotlinx.android.synthetic.main.profile_post_layout.view.*

class DetailedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var post: Post? = null

    fun bindPost(followerPost : Post){
        this.post = post
        Picasso.get().load(followerPost.profile.picture).transform(CircleTrasformation()).into(view.Post_user_profile)
        if (followerPost.picture.isNotEmpty()) {
            Picasso.get().load(followerPost.picture).into(view.Post_user)
        }else{
            itemView.Post_user.setImageResource(R.drawable.ic_account_circle_black_24dp)
        }

    }
        override fun onClick(v: View?) {
        }

}