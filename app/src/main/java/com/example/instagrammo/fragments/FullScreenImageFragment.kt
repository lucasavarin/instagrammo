package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.instagrammo.R
import com.example.instagrammo.util.DOWNLOAD_URL
import com.example.instagrammo.util.DOWNLOAD_URL_REFORMED
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image_full_screen.*
import kotlinx.android.synthetic.main.fragment_modifyprofile.backButton

class FullScreenImageFragment:Fragment() {

    lateinit var downloadUrl:String
    lateinit var downloadUrlReformed:String

    companion object{
        fun makeInstance():FullScreenImageFragment{
            return FullScreenImageFragment()
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
        if(arguments != null){
            downloadUrl = arguments!!.getString(DOWNLOAD_URL, "")
            downloadUrlReformed = arguments!!.getString(DOWNLOAD_URL_REFORMED, "")
        }
        backFullScreen.setOnClickListener {
            val fragmentManager: FragmentManager = activity !!.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val fragment = AddFragment()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        nextFullScreen.setOnClickListener {
            val fragmentManager: FragmentManager = activity !!.supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val fragment = DescriptionFragment()
            fragment.arguments = this.arguments
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        Picasso.get().load(downloadUrl).into(imageFullScreen)
    }
}