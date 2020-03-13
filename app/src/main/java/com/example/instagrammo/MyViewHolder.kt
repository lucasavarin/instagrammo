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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


fun fillData(s : Payload){

        Picasso.get().load(s.picture).resize(300,300).centerInside()
            .transform(CircleTrasformation()).into(v.recyclerViewFollowers)
}


}