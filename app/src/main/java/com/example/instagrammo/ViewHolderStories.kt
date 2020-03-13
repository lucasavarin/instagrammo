package com.example.instagrammo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.util.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*



class ViewHolderStories (val v: View) : RecyclerView.ViewHolder(v) , View.OnClickListener{

    override fun onClick(v: View?) {
    }


    fun fillStories(s : Profilo){

        Picasso.get().load(s.picture).fit()
            .transform(CircleTrasformation()).into(v.recyclerViewFollowers)
    }


}