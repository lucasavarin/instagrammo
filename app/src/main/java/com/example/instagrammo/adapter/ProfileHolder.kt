package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.business.ProfileRest
import com.example.instagrammo.util.picassotransformation.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profilo_fragment_layout.view.*

class ProfileHolder (v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var posts : ProfileRest? = null

    fun bindProfilePost(post: ProfileRest){
        this.posts = post
        if (post.picture.isNotEmpty() || view.Bio.text.isNotEmpty() ||view.Post_number.text.isNotEmpty() || view.Friend_number.text.isNotEmpty()) {
            Picasso.get().load(post.picture).transform(CircleTrasformation()).into(view.Profile_photo)
            view.Post_number.text = post.postsNumber
            view.Friend_number.text = post.followersNumber
            view.Bio.text = post.description
            view.Profile_name.text = post.name
        }else{
            view.Profile_photo.setImageResource(R.drawable.ic_account_circle_black_24dp)
            view.Post_number.setText("0")
            view.Friend_number.setText("0")
            view.Bio.setText("campo vuoto")
            view.Profile_name.setText("senza nome")
        }
    }

    override fun onClick(p0: View?) {

    }
}
