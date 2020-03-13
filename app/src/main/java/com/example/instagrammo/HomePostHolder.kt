package com.example.instagrammo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.HomePayloadPostBean
import com.example.instagrammo.util.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class HomePostHolder(item : View) : RecyclerView.ViewHolder(item), View.OnClickListener {
    private var view : View = item

    private var posts : HomePayloadPostBean? = null
    fun bindFollower(followerPost : HomePayloadPostBean){
        this.posts = followerPost
        view.Profile_name.text = followerPost.profile.name
        view.Post_title.text = followerPost.title
        view.Post_hour.text = followerPost.uploadTime
        Picasso.get().load(followerPost.picture).into(view.Post_user)
        Picasso.get().load(followerPost.profile.picture).transform(CircleTrasformation()).into(view.Post_user_profile)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
