package com.instagrammo.view.home_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.instagrammo.bean.buissnes.HomePayloadPostBean
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_follower_story.view.*
import java.lang.Exception

class HomeFollowerPostHolder(itemView: View)  : RecyclerView.ViewHolder(itemView){

    private var view: View = itemView
    private var followerPost: HomePayloadPostBean? = null

    fun bindFollowerPost(followerPost : HomePayloadPostBean){
        this.followerPost = followerPost
        view.progressHome.visibility = View.VISIBLE
        if(followerPost.picture != ""){

            Picasso.get().load(followerPost.picture).into(view.userPost, object : Callback{
                override fun onSuccess() {
                    view.progressHome.visibility = View.INVISIBLE
                }
                override fun onError(e: Exception?) {}
            })

            Picasso.get().load(followerPost.HomeProfilePostBean.picture).transform(CircleTransform()).into(view.userPostProfile)
        }else{
            view.userPost.setImageResource(R.drawable.placeholder)
            view.userPostProfile.setImageResource(R.drawable.placeholder)
        }
        view.nameProfile.setText(followerPost.HomeProfilePostBean.name)
        view.titlePost.setText(followerPost.title)
        view.oraPost.setText(followerPost.uploadTime)
    }
}