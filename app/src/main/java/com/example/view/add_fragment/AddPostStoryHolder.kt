package com.example.view.add_fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.AddPostResponseBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycle_view_add.view.*
import kotlinx.android.synthetic.main.modify_profile_layout.*

class AddPostStoryHolder(itemView: View) :  RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private var view : View = itemView
    private var posts : AddPostResponseBean? = null

    fun bindPosts(posts : AddPostResponseBean){
        this.posts = posts
        Picasso.get().load(posts.download_url).into(view.addPostsList)
    }

    override fun onClick(v: View?) {

    }

}