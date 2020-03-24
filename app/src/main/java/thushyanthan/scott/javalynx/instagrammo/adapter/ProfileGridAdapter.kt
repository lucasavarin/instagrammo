package thushyanthan.scott.javalynx.instagrammo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.util.rest.PayloadProfile
import thushyanthan.scott.javalynx.instagrammo.util.rest.PostPayload

class ProfileGridAdapter (val postsProfile: List<PostPayload>, val context: Context): RecyclerView.Adapter<ProfileGridHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileGridHolder {
        return ProfileGridHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.profile_grid_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileGridHolder, position: Int) {
        holder.assemblePosts(postsProfile[position])
    }

    override fun getItemCount(): Int {
        return postsProfile.size
    }
}