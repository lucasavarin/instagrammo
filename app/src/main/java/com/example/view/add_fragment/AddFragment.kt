package com.example.view.add_fragment

import android.os.Bundle
import android.service.voice.AlwaysOnHotwordDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.AddPostResponseBean
import com.example.login.R
import com.example.util.retrofit.ClientIterceptorAdd
import kotlinx.android.synthetic.main.add_layout.*
import kotlinx.android.synthetic.main.add_layout_click_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddFragment : Fragment() {

    private lateinit var gidLayout: GridLayoutManager
    private var postList : MutableList<AddPostResponseBean> =  ArrayList<AddPostResponseBean>()
    private  lateinit var adapterAddPost : AddPostStoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        createPosts(postList)
        return inflater.inflate(R.layout.add_layout, container, false)

    }

    private fun createPosts(list : MutableList<AddPostResponseBean>) {

        ClientIterceptorAdd.getUserAdd.getAddPost()
            .enqueue(object : Callback<List<AddPostResponseBean>> {
                override fun onFailure(call: Call<List<AddPostResponseBean>>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "C'è stato un errore nella Recezione delle immagini",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(
                    call: Call<List<AddPostResponseBean>>,
                    response: Response<List<AddPostResponseBean>>
                ) {

                    list.addAll(resizeImage(response))
                    createAddPosts(list);
                }
            })
    }

    private fun createAddPosts(listFiltered : List<AddPostResponseBean>): RecyclerView {

        gidLayout = GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL, false)
        addPost.layoutManager = gidLayout

        adapterAddPost  = AddPostStoryAdapter(listFiltered)
        adapterAddPost.setOnAddPostScrollListener { createPosts(postList) }

        addPost.adapter = adapterAddPost


        return addPost
    }

    private fun resizeImage(response: Response<List<AddPostResponseBean>>): MutableList<AddPostResponseBean> {

        val lista: MutableList<AddPostResponseBean>? = ArrayList<AddPostResponseBean>()

        for (items in response.body()!!) {

            SessionAddFragmentData.urlImage.add(items.download_url)

            items.download_url = "https://picsum.photos/id/${items.id}/400/400"

            SessionAddFragmentData.createPostUrl.add(items.download_url)

            lista!!.add(items)
        }

        return lista!!
    }


}

