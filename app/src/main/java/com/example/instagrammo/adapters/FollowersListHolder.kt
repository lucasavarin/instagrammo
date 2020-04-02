package com.example.instagrammo.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.Follower
import com.example.instagrammo.util.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.followers_layout_item.view.*

class FollowersListHolder(v:View):RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var follower: Follower? = null

    fun bindFollower(follower: Follower){
        this.follower = follower
        if(follower.picture.isNotEmpty()) {
            Picasso.get().load(follower.picture).transform(CircleTransform()).into(view.followerImage)
        }else{
            Picasso.get().load(R.mipmap.no_profile_pic).transform(CircleTransform()).into(view.followerImage)
        }
    }


    override fun onClick(p0: View?) {
        Toast.makeText(view.context, follower.toString(), Toast.LENGTH_LONG).show()
    }
}