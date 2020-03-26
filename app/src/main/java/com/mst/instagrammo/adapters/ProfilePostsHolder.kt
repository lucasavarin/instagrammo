package com.mst.instagrammo.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.model.beans.HomePost

class ProfilePostsHolder (v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    var profilepost: HomePost? = null

    override fun onClick(v: View?) {
        Toast.makeText(view.context, profilepost.toString(), Toast.LENGTH_LONG).show()
    }

    fun bind(profilepost: HomePost){
        this.profilepost = profilepost
//        Picasso.get().load(profilepost.picture).into(view.post_img_items)

//        Picasso.get().load(profilepost.profile.picture).transform(CircleTransform()).into(view.post_propic)
//        view.post_name.text = profilepost.profile.name
//        view.post_date.text = profilepost.uploadTime
//        view.post_title.text = profilepost.title
    }
}