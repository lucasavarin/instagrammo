package com.example.instagrammo.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.business.ProfilePost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_layout_item.view.*

class PostGridHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var post: ProfilePost? = null

    fun bindPost(post: ProfilePost){
        this.post = post
        Picasso.get().load(post.picture).into(view.image)
    }

    override fun onClick(p0: View?) {

    }
}