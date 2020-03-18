package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile_grid_layout.view.*
import kotlinx.android.synthetic.main.profile_post_layout.view.*
import kotlinx.android.synthetic.main.profilo_fragment_layout.view.*

class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view : View = itemView
    private var post : Post? = null



    override fun onClick(v: View?) {

    }
}