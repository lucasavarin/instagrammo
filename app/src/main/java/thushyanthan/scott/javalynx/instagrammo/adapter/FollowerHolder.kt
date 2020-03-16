package thushyanthan.scott.javalynx.instagrammo.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.follower_list_item.view.*
import thushyanthan.scott.javalynx.instagrammo.util.rest.FollowerPayload
import thushyanthan.scott.javalynx.instagrammo.util.transformation.CircleTransformation

class FollowerHolder (view: View) : RecyclerView.ViewHolder(view){
    var v:View = view
    var follower: FollowerPayload? = null

    fun assembleFollower(follower: FollowerPayload){
        this.follower = follower

        if (follower.picture != "")
        Picasso.get().load(follower.picture).transform(CircleTransformation()).into(v.followerImg)
    }
}