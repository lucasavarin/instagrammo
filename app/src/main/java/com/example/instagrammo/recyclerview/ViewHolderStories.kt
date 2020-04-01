package com.example.instagrammo.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.data_class.ProfiloStories
import com.example.instagrammo.R
import com.example.instagrammo.util.CircleTrasformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.recyclerViewFollowers


class ViewHolderStories (val v: View) : RecyclerView.ViewHolder(v) , View.OnClickListener{

    override fun onClick(v: View?) {

    }

    fun fillStories(s : ProfiloStories) {

        if (s.picture.isEmpty()) {
            v.recyclerViewFollowers.setImageResource(R.drawable.baseline_apps_black_36)
        }else{
            Picasso.get().load(s.picture).fit()
                .transform(CircleTrasformation()).into(v.recyclerViewFollowers)
        }
    }

}