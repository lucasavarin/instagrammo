package com.example.instagrammo.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.business.Followers
import com.example.instagrammo.util.CircleTransform
import com.example.instagrammo.util.NO_PROFILE_PIC
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.followers_layout_item.view.*

class FollowersListHolder(v:View):RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var follower: Followers? = null

    fun bindFollower(follower: Followers){
        this.follower = follower
        if(follower.picture.isNotEmpty()) {
            Picasso.get().load(follower.picture).transform(CircleTransform()).into(view.followerImage)
        }else{
            Picasso.get().load(NO_PROFILE_PIC).transform(CircleTransform()).into(view.followerImage)
        }
    }


    override fun onClick(p0: View?) {
        Toast.makeText(view.context, follower.toString(), Toast.LENGTH_LONG).show()
    }
}