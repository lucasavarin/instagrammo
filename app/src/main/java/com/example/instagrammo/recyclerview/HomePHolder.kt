package com.example.instagrammo.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
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

        if (followerPost.picture!= ""){
            Picasso.get().load(followerPost.picture).into(view.user_post)
        }else{
            view.user_post.setBackgroundResource(R.drawable.baseline_view_stream_black_48)
        }
        if (followerPost.profile.picture!=""){
            Picasso.get().load(followerPost.profile.picture).transform(CircleTrasformation()).into(view.user_profile_image)
        }else{
            view.user_profile_image.setBackgroundResource(R.drawable.baseline_apps_black_18)
        }
    }

    override fun onClick(v: View?) {
    }
}
