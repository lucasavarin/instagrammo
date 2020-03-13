package thushyanthan.scott.javalynx.instagrammo.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_list_item.view.*
import thushyanthan.scott.javalynx.instagrammo.PostPayload
import thushyanthan.scott.javalynx.instagrammo.R

class HomeAdapter (val posts:List<PostPayload>, val context:Context) : RecyclerView.Adapter<ViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.assemblePost(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var v:View = view
    var post: PostPayload? = null

    fun assemblePost(post:PostPayload){
        this.post = post
        Picasso.get().load(post.picture).into(v.imagePost)
        v.titlePost.text = post.title
        v.datePost.text = post.uploadTime
        v.username.text = post.profile.name
    }
}