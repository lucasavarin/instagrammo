package com.mst.instagrammo.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mst.instagrammo.model.beans.Story
import com.mst.instagrammo.utilities.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_home_stories_items.view.*

class StoriesHolder (v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    var story: Story? = null

    override fun onClick(v: View?) {
        Toast.makeText(view.context, story.toString(), Toast.LENGTH_LONG).show()
    }

    fun bind(story: Story){
        this.story = story
        Picasso.get().load(story.picture).transform(CircleTransform()).into(view.storiesItems)
    }
}