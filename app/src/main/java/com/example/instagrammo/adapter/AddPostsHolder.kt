package com.example.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.model.AddResponseBeanApplicativo
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_post_item_layout.view.*
import java.lang.Exception

class AddPostsHolder(val v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{
    override fun onClick(v: View?) {

    }


    fun bindView(item:AddResponseBeanApplicativo , func:((AddResponseBeanApplicativo)->Unit)?){
        v.progressBarAdd.visibility = View.VISIBLE
        Picasso.get().load(item.urlModificato).into(v.postAdd,object:Callback{
            override fun onError(e: Exception?) {

            }

            override fun onSuccess() {
                v.progressBarAdd.visibility = View.GONE
            }
        })

        v.setOnClickListener{
            func?.invoke(item)
        }
    }

}