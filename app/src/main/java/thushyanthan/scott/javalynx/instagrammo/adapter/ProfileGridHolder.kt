package thushyanthan.scott.javalynx.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.post_list_item.view.*
import kotlinx.android.synthetic.main.profile_grid_list_item.view.*
import thushyanthan.scott.javalynx.instagrammo.util.rest.PayloadProfile
import thushyanthan.scott.javalynx.instagrammo.util.rest.PostPayload
import thushyanthan.scott.javalynx.instagrammo.util.transformation.CircleTransformation

class ProfileGridHolder (view: View) :  RecyclerView.ViewHolder(view){
    var v: View = view
    var post: PostPayload? = null

    fun assemblePosts(post: PostPayload){
        this.post = post
        if(post.picture != ""){
            Picasso.get().load(post.picture).into(v.imagePostProfile)
        }
    }
}