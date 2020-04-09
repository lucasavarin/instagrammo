package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagrammo.R
import com.example.instagrammo.adapters.PostsListRecyclerAdapter
import com.example.instagrammo.beans.business.Post
import kotlinx.android.synthetic.main.fragment_grid_profile.*
import kotlinx.android.synthetic.main.fragment_list_profile.*

class ListProfileFragment: Fragment() {

    var posts: List<Post> = ArrayList()
    var adapter: PostsListRecyclerAdapter = PostsListRecyclerAdapter(posts.toMutableList())

    companion object{
        fun makeInstance(): ListProfileFragment{
            return ListProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_list_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        list.adapter = adapter
    }
}