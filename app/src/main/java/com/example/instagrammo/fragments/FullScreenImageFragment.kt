package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.instagrammo.R
import com.example.instagrammo.activities.MainActivity
import com.example.instagrammo.util.DOWNLOAD_URL
import com.example.instagrammo.util.DOWNLOAD_URL_REFORMED
import com.example.instagrammo.util.replaceFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image_full_screen.*
import kotlinx.android.synthetic.main.fragment_modifyprofile.backButton

class FullScreenImageFragment private constructor(private val downloadUrl:String, private val downloadUrlReformed:String):Fragment() {

    companion object{
        fun makeInstance(downloadUrl:String,
                         downloadUrlReformed:String):FullScreenImageFragment{
            return FullScreenImageFragment(downloadUrl,downloadUrlReformed)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_image_full_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backFullScreen.setOnClickListener {
            (activity as MainActivity).replaceFragment(AddFragment.makeInstance(), R.id.container, "")
        }

        nextFullScreen.setOnClickListener {
            (activity as MainActivity).replaceFragment(DescriptionFragment.makeInstance(downloadUrl, downloadUrlReformed), R.id.container, "")
        }

        Picasso.get().load(downloadUrl).into(imageFullScreen)
    }
}