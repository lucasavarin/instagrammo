package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.picassotransformation.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_layout_home.view.*
import com.example.instagrammo.model.Post

class HomeFollowerPostHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var followerPost: Post? = null

    fun bindFollowerPost(followerPost : Post){
        this.followerPost = followerPost
        view.Profile_name.text = followerPost.profile.name
        view.Post_title.text = followerPost.title
        view.Post_hour.text = followerPost.uploadTime
        Picasso.get().load(followerPost.picture).into(view.Post_user)
        Picasso.get().load(followerPost.profile.picture).transform(CircleTrasformation()).into(view.Post_user_profile)
    }


    override fun onClick(v: View?) {

    }

}