package com.mst.instagrammo.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.model.beans.HomePost
import com.mst.instagrammo.utilities.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_posts_items_list.view.*

class HomePostsHolder (v: View): RecyclerView.ViewHolder(v) {
    private var view: View = v
    var homepost: HomePost? = null


    fun bind(homepost: HomePost){
        this.homepost = homepost
        Picasso.get().load(homepost.picture).into(view.post_img_items)
        Picasso.get().load(homepost.profile.picture).transform(CircleTransform()).into(view.post_propic)
        view.post_name.text = homepost.profile.name
        view.post_date.text = homepost.uploadTime
        view.post_title.text = homepost.title
    }
}