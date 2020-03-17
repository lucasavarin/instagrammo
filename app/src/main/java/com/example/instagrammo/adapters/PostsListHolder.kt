package com.example.instagrammo.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.util.CircleTransform
import com.example.instagrammo.util.NO_IMAGE_AVAILABLE
import com.example.instagrammo.util.NO_PROFILE_PIC
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_layout_item.view.*

class PostsListHolder(v:View):RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var post: Post? = null

    fun bindPost(post: Post){
        this.post = post
        if(post.picture.isNotEmpty()) {
            Picasso.get().load(post.picture).into(view.img)
        }else {
            Picasso.get().load(NO_IMAGE_AVAILABLE).into(view.img)
        }
        if(post.profile == null){
            view.propic.visibility = View.GONE
            view.title.visibility = View.GONE
            view.date.visibility = View.GONE
            view.name.visibility = View.GONE
        }else if(post.profile.picture.isNotEmpty()) {
            Picasso.get().load(post.profile.picture).transform(CircleTransform()).into(view.propic)
            view.title.text = post.title
            view.date.text = post.uploadTime
            view.name.text = post.profile.name
        }else{
            Picasso.get().load(NO_PROFILE_PIC).transform(CircleTransform()).into(view.propic)
        }


    }


    override fun onClick(p0: View?) {
        Toast.makeText(view.context, post.toString(), Toast.LENGTH_LONG).show()
    }
}