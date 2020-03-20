package com.example.instagrammo.primary_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.AddPostResponse
import com.example.instagrammo.recyclerview.AddPAdapter
import com.example.instagrammo.retrofit.AddPostClient
import kotlinx.android.synthetic.main.fragment_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AddFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        AddPostClient.getAddClient.getAddPost().enqueue(object : Callback<List<AddPostResponse>>{
            override fun onFailure(call: Call<List<AddPostResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<AddPostResponse>>,
                response: Response<List<AddPostResponse>>
            ) {
                addPost(response)
                resizePost(response)
            }

        })
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    companion object{
        fun makeInstance():Fragment {
            return AddFragment()
        }
    }

    private fun addPost(response: Response<List<AddPostResponse>>) : RecyclerView {
        val gridLayoutManager = GridLayoutManager(this.context, 3)
        grid_recycle.layoutManager = gridLayoutManager

        val adapterPosts =
            AddPAdapter(response.body()!!)
        grid_recycle.adapter = adapterPosts

        return grid_recycle
    }

    private fun resizePost(response : Response<List<AddPostResponse>>) : MutableList<AddPostResponse>{
        val listaPost : MutableList<AddPostResponse>? = ArrayList<AddPostResponse>()
        val width = 400
        val height = 400

        for (i : AddPostResponse in response.body()!!){
            i.download_url = "https://picsum.photos/id/${i.id}/${width}/${height}"
            listaPost!!.add(i)
        }
        return listaPost!!
    }
}