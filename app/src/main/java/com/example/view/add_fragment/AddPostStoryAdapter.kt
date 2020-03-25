package com.example.view.add_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.AddPostResponseBean
import com.example.login.R


class AddPostStoryAdapter(private val dataList : List<AddPostResponseBean>) : RecyclerView.Adapter<AddPostStoryHolder>(){

    private lateinit var context : Context

    private var callback : (() -> Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AddPostStoryHolder {
        context = p0.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_recycle_view_add, p0,false)
        return AddPostStoryHolder(inflatedView)

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(p0: AddPostStoryHolder, p1: Int) {
        p0.bindPosts(dataList.get(p1))

        p0.itemView.setOnClickListener { view ->

            val activity = view.context as AppCompatActivity
            val myFragment: Fragment = AddSecondFragment()
            SessionAddFragmentData.position = p1
            activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit()


        }

        if(p1 >= itemCount - 5  ){
          callback?.invoke()
        }

    }

    fun setOnAddPostScrollListener(callback : () -> Unit){
        this.callback = callback
    }

}



