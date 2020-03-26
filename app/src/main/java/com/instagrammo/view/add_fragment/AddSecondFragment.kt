package com.instagrammo.view.add_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.instagrammo.bean.buissnes.AddPostResponseBean
import com.example.login.R
import com.instagrammo.util.utilities_project
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_layout_click_item.*
import kotlinx.android.synthetic.main.add_layout_click_item.view.*
import kotlinx.android.synthetic.main.custom_view_layout.*

class AddSecondFragment(private val addBeanSessionFragment: AddPostResponseBean)  : Fragment() {

    companion object {
        fun makeInstance(it: AddPostResponseBean): Fragment {
            return AddSecondFragment(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View  {

        val rootestView : View = inflater.inflate(R.layout.add_layout_click_item,container ,false)

        Picasso.get().load(addBeanSessionFragment.url).into(rootestView.imageClick)

        return rootestView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.setOnClickListener { utilities_project.deleteFragment(AddSecondFragment(addBeanSessionFragment), activity!!) }

        createPost.setOnClickListener { utilities_project.addFragment(AddThirdFragment(addBeanSessionFragment), activity!!)}


    }

}