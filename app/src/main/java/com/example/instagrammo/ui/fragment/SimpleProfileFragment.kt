package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instagrammo.R
import com.example.instagrammo.adapter.SimpleViewAdapter
import com.example.instagrammo.model.ProfilePost
import kotlinx.android.synthetic.main.profile_grid_layout.*

class SimpleProfileFragment : Fragment(){

    var posts: MutableList<ProfilePost> = ArrayList()

    var adapter: SimpleViewAdapter = SimpleViewAdapter(posts)

    companion object{
        fun makeInstance(): SimpleProfileFragment{
            return SimpleProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.profile_grid_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridview.layoutManager = GridLayoutManager(context, 3)
        gridview.adapter = adapter

    }
}