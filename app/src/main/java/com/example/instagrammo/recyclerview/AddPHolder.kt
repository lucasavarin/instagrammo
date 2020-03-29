package com.example.instagrammo.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.response.AddPostResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_post_item_layout.view.*

class AddPHolder(private val view: View) : RecyclerView.ViewHolder(view){

    private var posts : AddPostResponse? = null


    fun fillGrid(posts : AddPostResponse){
        this.posts = posts
        Picasso.get().load(posts.download_url).fit().into(view.add_post)
    }
}