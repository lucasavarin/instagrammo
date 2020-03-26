package com.example.instagrammo.adapters

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.beans.business.ProfilePost
import com.example.instagrammo.util.normalizeDownloadUrlToScreenWidth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_layout_item.view.*


class PostGridHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var post: ProfilePost? = null

    fun bindPost(post: ProfilePost){
        this.post = post
        val dip = 140f
        val r: Resources = view.resources
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            r.displayMetrics
        )
        Picasso.get().load(normalizeDownloadUrlToScreenWidth(post.picture, px.toInt())).into(view.image)
    }

    override fun onClick(p0: View?) {

    }
}