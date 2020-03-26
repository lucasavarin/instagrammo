package com.instagrammo.view.add_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.instagrammo.bean.buissnes.AddPostResponseBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycle_view_add.view.*


class AddPostStoryHolder(private val view : View) :  RecyclerView.ViewHolder(view){

    private var posts : AddPostResponseBean? = null

    fun bindPosts(posts : AddPostResponseBean){
        this.posts = posts
        Picasso.get().load(posts.download_url).into(view.addPostsList)

    }

}