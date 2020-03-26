package com.example.instagrammo.adapters

import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagrammo.beans.business.Post
import com.example.instagrammo.util.CircleTransform
import com.example.instagrammo.util.NO_IMAGE_AVAILABLE
import com.example.instagrammo.util.NO_PROFILE_PIC
import com.example.instagrammo.util.normalizeDownloadUrlToScreenWidth
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.post_layout_item.view.*
import java.lang.Exception

class PostsListHolder(v:View):RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var post: Post? = null

    fun bindPost(post: Post){
        this.post = post
        if(post.picture.isNotEmpty()) {
            val size = Point()
            (view.context as AppCompatActivity).windowManager.defaultDisplay.getSize(size)
            Picasso.get().load(normalizeDownloadUrlToScreenWidth(post.picture, size.x)).into(view.img)
            Picasso.get().load(normalizeDownloadUrlToScreenWidth(post.picture, size.x)).transform(BlurTransformation(view.context)).into(view.image_holder)
        }else {
            Picasso.get().load(NO_IMAGE_AVAILABLE).into(view.img)
        }
        if(post.profile == null){
            view.propic.visibility = View.GONE
            view.title.visibility = View.GONE
            view.date.visibility = View.GONE
            view.name.visibility = View.GONE
        }else if(post.profile.picture.isNotEmpty()) {
            Picasso.get().load(post.profile.picture).transform(CircleTransform()).into(view.propic)
            view.title.text = post.title
            view.date.text = post.uploadTime
            view.name.text = post.profile.name
        }else{
            Picasso.get().load(NO_PROFILE_PIC).transform(CircleTransform()).into(view.propic)
        }


    }


    override fun onClick(p0: View?) {
        Toast.makeText(view.context, post.toString(), Toast.LENGTH_LONG).show()
    }
}