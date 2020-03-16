package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.Post
import com.example.instagrammo.model.ProfileRest
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.android.synthetic.main.profile_post_layout.*
import kotlinx.android.synthetic.main.profile_post_layout.view.*
import kotlinx.android.synthetic.main.profilo_fragment_layout.view.*
import kotlinx.android.synthetic.main.splash_layout.view.*

class ProfileHolder (v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var posts : ProfileRest? = null

    fun bindProfilePost(post: ProfileRest){
        this.posts = post

        if (post.picture.isNotEmpty() || view.Post_number.text.isNotEmpty() || view.Friend_number.text.isNotEmpty()) {
            Picasso.get().load(post.picture).into(view.Profile_photo)
            view.Post_number.text = post.postsNumber
            view.Friend_number.text = post.followersNumber
        }else{
            view.Profile_photo.setImageResource(R.drawable.ic_account_circle_black_24dp)
            view.Post_number.setText("0")
            view.Friend_number.setText("0")
        }
    }

    override fun onClick(p0: View?) {

    }
}
