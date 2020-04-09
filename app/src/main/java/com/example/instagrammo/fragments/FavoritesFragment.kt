package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagrammo.R
import com.example.instagrammo.adapters.PostsListRecyclerAdapter
import com.example.instagrammo.applicationUtils.db
import com.example.instagrammo.beans.business.Post
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment: Fragment() {

    var posts = arrayListOf<Post>().toMutableList()
    val adapter = PostsListRecyclerAdapter(posts)
    private val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    companion object{
        fun makeInstance():FavoritesFragment {
            return FavoritesFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesListView.layoutManager = linearLayoutManager
        favoritesListView.adapter = adapter
        adapter.setOnItemLongClickListener {
            it.like = !(it.like != null && it.like!!)
            db.likePost(it, it.like)
        }
        adapter.setOnLikeChangeListener {
            it.like = !(it.like != null && it.like!!)
            db.likePost(it, it.like)
        }
        updateData()
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    fun updateData(){
        posts = db.getLikedPosts().toMutableList()
        adapter.posts = posts
        favoritesListView.adapter?.notifyDataSetChanged()
    }

}