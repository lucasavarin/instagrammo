package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.adapters.PostGridRecyclerAdapter
import com.example.instagrammo.beans.business.ProfilePost
import kotlinx.android.synthetic.main.fragment_grid_profile.*

class GridProfileFragment : Fragment(){

    var posts: List<ProfilePost> = ArrayList()

    var adapter: PostGridRecyclerAdapter = PostGridRecyclerAdapter(posts)

    companion object{
        fun makeInstance(): GridProfileFragment{
            return GridProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_grid_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        grid.layoutManager = GridLayoutManager(context, 3)
        grid.adapter = adapter

    }
}