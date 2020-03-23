package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.picassotransformation.CircleTrasformation
import com.squareup.picasso.Picasso
import com.example.instagrammo.model.Payload
import kotlinx.android.synthetic.main.item_follower_view_layout.view.*



class MyViewHolder (val v: View) : RecyclerView.ViewHolder(v) , View.OnClickListener{

    override fun onClick(v: View?) {
    }


fun fillData(s : Payload){
        if (s.picture.isNotEmpty()) {
            Picasso.get().load(s.picture).resize(175, 175).centerInside()
                .transform(CircleTrasformation()).into(v.recyclerViewFollowers)
        }else{
            v.recyclerViewFollowers.setImageResource(R.drawable.ic_account_circle_black_24dp)
        }
}


}