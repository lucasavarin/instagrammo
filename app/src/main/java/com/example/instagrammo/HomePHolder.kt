package com.example.instagrammo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.HomePayloadPostBean
import com.example.instagrammo.util.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class HomePHolder(item : View) : RecyclerView.ViewHolder(item), View.OnClickListener {
    private var view : View = item

    private var posts : HomePayloadPostBean? = null
    fun assemblePost(followerPost : HomePayloadPostBean){
        this.posts = followerPost

        view.user_name.text = followerPost.profile.name
        view.post_title.text = followerPost.title
        view.post_hour.text = followerPost.uploadTime

        Picasso.get().load(followerPost.picture).into(view.user_post)
        Picasso.get().load(followerPost.profile.picture).transform(CircleTrasformation()).into(view.user_profile_image)
    }

    override fun onClick(v: View?) {
    }
}
