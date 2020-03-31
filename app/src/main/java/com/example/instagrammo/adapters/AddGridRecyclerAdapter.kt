package com.example.instagrammo.adapters

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.activities.MainActivity
import com.example.instagrammo.beans.business.AddPost
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.beans.business.ProfilePost
import com.example.instagrammo.beans.response.AddPostResponseBeanREST
import com.example.instagrammo.fragments.FullScreenImageFragment
import com.example.instagrammo.util.DOWNLOAD_URL
import com.example.instagrammo.util.DOWNLOAD_URL_REFORMED
import com.example.instagrammo.util.inflate
import com.example.instagrammo.util.replaceFragment

class AddGridRecyclerAdapter(private var posts: List<AddPost>): RecyclerView.Adapter<AddGridHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddGridHolder {
        val inflatedView = parent.inflate(R.layout.grid_layout_item, false)
        inflatedView.setOnClickListener {

        }
        return AddGridHolder(inflatedView)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: AddGridHolder, position: Int) {
        holder.bindPost(posts[position])
        holder.itemView.setOnClickListener {
            callback?.invoke(posts[position])
        }
    }

    private var callback:((AddPost) -> Unit)? = null

    fun setOnImageClickedListener(callback: (AddPost) -> Unit){
        this.callback = callback
    }
}