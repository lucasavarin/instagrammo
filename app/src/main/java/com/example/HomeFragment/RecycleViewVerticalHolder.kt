package com.example.HomeFragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_layout.view.*
import kotlinx.android.synthetic.main.item_horizontal_view.view.*

class RecycleViewVerticalHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private var view : View = itemView
    private var follower : HomeUserResponseBean? = null

    fun bindFollower(follower : HomeUserResponseBean){
        this.follower = follower
        Picasso.get().load(follower.pictureUser).into(view.userStory)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}