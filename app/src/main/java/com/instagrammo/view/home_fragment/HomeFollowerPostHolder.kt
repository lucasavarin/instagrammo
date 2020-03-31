package com.instagrammo.view.home_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.HomePayloadPostBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_follower_story.view.*

class HomeFollowerPostHolder(itemView: View)  : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var followerPost: HomePayloadPostBean? = null

    fun bindFollowerPost(followerPost : HomePayloadPostBean){
        this.followerPost = followerPost
        if(followerPost.picture != ""){
            Picasso.get().load(followerPost.picture).into(view.userPost)
            Picasso.get().load(followerPost.HomeProfilePostBean.picture).transform(CircleTransform()).into(view.userPostProfile)
        }
        view.nameProfile.setText(followerPost.HomeProfilePostBean.name)
        view.titlePost.setText(followerPost.title)
        view.oraPost.setText(followerPost.uploadTime)
    }


    override fun onClick(v: View?) {

    }

}