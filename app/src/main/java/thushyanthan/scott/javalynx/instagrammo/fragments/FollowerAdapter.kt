package thushyanthan.scott.javalynx.instagrammo.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thushyanthan.scott.javalynx.instagrammo.FollowerPayload
import thushyanthan.scott.javalynx.instagrammo.R

class FollowerAdapter (val followers:List<FollowerPayload>, val context: Context): RecyclerView.Adapter<FollowerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerHolder {
        return FollowerHolder(LayoutInflater.from(context).inflate(R.layout.follower_list_item,parent,false))
    }

    override fun onBindViewHolder(holder: FollowerHolder, position: Int) {
        holder.assembleFollower(followers[position])
    }

    override fun getItemCount(): Int {
        return followers.size
    }
}