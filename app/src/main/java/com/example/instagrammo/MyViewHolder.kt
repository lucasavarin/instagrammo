package com.example.instagrammo

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import model.Payload
import model.StoriesResponse
import kotlinx.android.synthetic.main.item_list_view_layout.view.*



class MyViewHolder (val v: View) : RecyclerView.ViewHolder(v) , View.OnClickListener{

    override fun onClick(v: View?) {
    }


fun fillData(s : Payload){

        Picasso.get().load(s.picture).resize(175,175).centerInside()
            .transform(CircleTrasformation()).into(v.recyclerViewFollowers)
}


}