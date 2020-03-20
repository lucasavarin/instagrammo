package com.example.instagrammo.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.AddPostResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_post_item_layout.view.*

class AddPHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view : View = itemView
    private var posts : AddPostResponse? = null

    override fun onClick(v: View?) {

    }

    fun fillGrid(posts : AddPostResponse){
        this.posts = posts
        Picasso.get().load(posts.download_url).fit().into(view.add_post)
    }
}