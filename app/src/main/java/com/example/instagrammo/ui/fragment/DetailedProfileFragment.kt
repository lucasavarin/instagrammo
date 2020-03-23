package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagrammo.R
import com.example.instagrammo.adapter.DetailedPostAdapter
import com.example.instagrammo.model.ProfilePost
import kotlinx.android.synthetic.main.profile_post_layout.*

class DetailedProfileFragment: Fragment() {

    var posts: MutableList<ProfilePost> = ArrayList()
    var adapter: DetailedPostAdapter = DetailedPostAdapter(posts)

    companion object{
        fun makeInstance(): DetailedProfileFragment{
            return DetailedProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.profile_post_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        listview.adapter = adapter
    }

}