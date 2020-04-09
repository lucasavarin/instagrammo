package com.example.instagrammo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.model.business.ProfileRest

class ProfileAdapter(private val posts: List<ProfileRest>): RecyclerView.Adapter<ProfileHolder>() {

    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
        context = parent.context
        val inflatedView =
            LayoutInflater.from(context).inflate(R.layout.profilo_fragment_layout, parent, false)
        return ProfileHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(profile: ProfileHolder, position: Int) {
        profile.bindProfilePost(posts[position])
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}