package com.example.instagrammo.adapters

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.activities.MainActivity
import com.example.instagrammo.beans.business.AddPost
import com.example.instagrammo.beans.business.ProfilePost
import com.example.instagrammo.fragments.FullScreenImageFragment
import com.example.instagrammo.util.DOWNLOAD_URL
import com.example.instagrammo.util.DOWNLOAD_URL_REFORMED
import com.example.instagrammo.util.normalizeDownloadUrlToScreenWidth
import com.example.instagrammo.util.replaceFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.grid_layout_item.view.*


class AddGridHolder(v: View): RecyclerView.ViewHolder(v) {

    private var view: View = v
    private var post: AddPost? = null

    fun bindPost(post: AddPost){
        this.post = post
        Picasso.get().load(post.downloadUrlReformed).into(view.image)
    }

}