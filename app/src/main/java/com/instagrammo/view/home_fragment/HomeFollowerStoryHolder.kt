package com.instagrammo.view.home_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.HomeUserResponseBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_horizontal_view.view.*

class HomeFollowerStoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private var view : View = itemView
    private var follower : HomeUserResponseBean? = null

    fun bindFollower(follower : HomeUserResponseBean){
        this.follower = follower
        if(follower.pictureUser != ""){
            Picasso.get().load(follower.pictureUser).transform(CircleTransform()).into(view.userStory)
        }

    }

    override fun onClick(v: View?) {

    }


}