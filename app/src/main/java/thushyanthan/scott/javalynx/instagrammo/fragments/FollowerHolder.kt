package thushyanthan.scott.javalynx.instagrammo.fragments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.follower_list_item.view.*
import thushyanthan.scott.javalynx.instagrammo.FollowerPayload

class FollowerHolder (view: View) : RecyclerView.ViewHolder(view){
    var v:View = view
    var follower: FollowerPayload? = null

    fun assembleFollower(follower:FollowerPayload){
        this.follower = follower
        Picasso.get().load(follower.picture).into(v.followerImg)
    }
}