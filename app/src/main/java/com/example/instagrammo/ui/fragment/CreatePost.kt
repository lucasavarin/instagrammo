package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.model.AddPostInfo
import com.squareup.picasso.Picasso

class CreatePost(val post : AddPostInfo) : Fragment() {

    companion object {

        fun newInstance(): AddFragment {
            val fragment = AddFragment()

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val confView : View = inflater.inflate(R.layout.confirm_add_layout, container, false)
        Picasso.get()
        return confView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }
    }
