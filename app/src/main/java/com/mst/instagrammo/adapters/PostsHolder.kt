package com.mst.instagrammo.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.model.beans.Post
import com.mst.instagrammo.utilities.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_home_posts_items.view.*

class PostsHolder (v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    var post: Post? = null

    override fun onClick(v: View?) {
        Toast.makeText(view.context, post.toString(), Toast.LENGTH_LONG).show()
    }

    fun bind(post: Post){
        this.post = post
        Picasso.get().load(post.picture).into(view.post_img_items)

        Picasso.get().load(post.profile.picture).transform(CircleTransform()).into(view.post_propic)
        view.post_name.text = post.profile.name
        view.post_date.text = post.uploadTime
        view.post_title.text = post.title
    }
}