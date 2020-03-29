package com.example.instagrammo.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.util.AddPostData
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.AddPostResponse
import com.example.instagrammo.secondary_fragments.AddPostDetailFragment

class AddPAdapter(var postData : List<AddPostResponse>) :
    RecyclerView.Adapter<AddPHolder>() {

    private lateinit var context : Context
    private var callBackOnItemClickListener : ((AddPostResponse) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPHolder {
        context = parent.context
        val lf = LayoutInflater.from(parent.context).inflate(R.layout.add_post_item_layout,parent,false)
        return AddPHolder(lf)
    }

    override fun getItemCount(): Int = postData.size

    override fun onBindViewHolder(holder: AddPHolder, position: Int) {
        holder.fillGrid(postData[position])

        holder.itemView.setOnClickListener{
            callBackOnItemClickListener?.invoke(postData[position])
        }
    }

    fun callBackOnItemClickListener(callBackOnClick : (AddPostResponse) -> Unit){
        this.callBackOnItemClickListener = callBackOnClick
    }
}