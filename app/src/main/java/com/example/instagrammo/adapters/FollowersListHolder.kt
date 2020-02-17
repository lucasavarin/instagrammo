package com.example.instagrammo.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.Followers
import com.example.instagrammo.util.CircleTransform
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.android.synthetic.main.followers_layout_item.view.*

class FollowersListHolder(v:View):RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var follower: Followers? = null

    fun bindFollower(follower: Followers){
        this.follower = follower
        Picasso.get().load(follower.picture).transform(CircleTransform()).into(view.followerImage)
    }


    override fun onClick(p0: View?) {
        Toast.makeText(view.context, follower.toString(), Toast.LENGTH_LONG).show()
    }
}