package com.example.instagrammo.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.util.inflate
import kotlinx.android.synthetic.main.post_layout_item.view.*

class PostsListRecyclerAdapter(var posts: MutableList<Post>): RecyclerView.Adapter<PostsListHolder>() {


    var callbackClick:((Post) -> Unit ) ? = null
    var callbackLongClick:((Post) -> Unit) ? = null
    var callbackLike:((Post) -> Unit) ? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsListHolder {
        val inflatedView = parent.inflate(R.layout.post_layout_item, false)
        return PostsListHolder(inflatedView)
    }

    override fun getItemCount()= posts.size

    override fun onBindViewHolder(holder: PostsListHolder, position: Int) {
        holder.bindPost(posts[position])
        holder.itemView.setOnClickListener {
            callbackClick?.invoke(posts[position])
        }
        holder.itemView.setOnLongClickListener {
            callbackLongClick?.invoke(posts[position])
            if(posts[position].like != null){
                it.likeButton.isChecked = posts[position].like!!
            }
            return@setOnLongClickListener true
        }
        holder.itemView.likeButton.setOnCheckedChangeListener { buttonView, isChecked ->
            callbackLike?.invoke(posts[position])
        }

        holder.itemView
    }

    fun setOnItemClickListener(callback:(Post)->Unit){
        this.callbackClick = callback
    }

    fun setOnLikeChangeListener(callback: (Post) -> Unit) {
        this.callbackLike = callback
    }

    fun setOnItemLongClickListener(callback: (Post) -> Unit){
        this.callbackLongClick = callback
    }
}