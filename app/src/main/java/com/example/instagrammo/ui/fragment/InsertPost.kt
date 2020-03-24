package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.model.AddResponseBeanApplicativo
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.inserisci_post_layout.*
import java.lang.Exception

class InsertPost(val item:AddResponseBeanApplicativo) : Fragment() {



    companion object{
        fun newInstance(item:AddResponseBeanApplicativo):InsertPost{
            return InsertPost(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inserisci_post_layout,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.background = resources.getDrawable(R.drawable.ic_arrow_forward_black_24dp)

        val nuovoUrlArray = item.downloadUrl.split("/")
        val nuovoUrl = nuovoUrlArray.mapIndexed lt@{ i,e ->
            if(i == nuovoUrlArray.size-1 || i == nuovoUrlArray.size-2)
                return@lt "900"
            else
                return@lt e
        }.joinToString ("/")
        progressBarAddPost.visibility = View.VISIBLE
        Picasso.get().load(nuovoUrl).into(imgadd,object:Callback{
            override fun onError(e: Exception?) {

            }

            override fun onSuccess() {
                progressBarAddPost.visibility = View.GONE
            }
        })
        customAdd.setOnBackClickListener { fragmentManager!!.beginTransaction().remove(this)
            .replace(R.id.frame,AddFragment.newInstance()).commit() }
    }
}