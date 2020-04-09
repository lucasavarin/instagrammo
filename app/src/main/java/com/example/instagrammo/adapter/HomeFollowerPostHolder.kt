package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.util.picassotransformation.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_layout_home.view.*
import com.example.instagrammo.model.business.Post

class HomeFollowerPostHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var followerPost: Post? = null

    fun bindFollowerPost(followerPost : Post){
        this.followerPost = followerPost
        view.Profile_name.text = followerPost.profile.name
        view.Post_title.text = followerPost.title
        view.Post_hour.text = followerPost.uploadTime
        if(followerPost.profile.picture.isNotEmpty())
                Picasso.get().load(followerPost.profile.picture).transform(CircleTrasformation()).into(view.Post_user_profile)
        if (followerPost.picture.isNotEmpty()) {
            Picasso.get().load(followerPost.picture).into(view.Post_user)
        }else{
            itemView.Post_user.setImageResource(R.drawable.ic_portable_wifi_off_black_24dp)
        }

    }


    override fun onClick(v: View?) {

    }

}