package com.example.view.add_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.AddPostResponseBean
import com.example.login.R
import com.example.util.retrofit.ClientIterceptorAdd
import kotlinx.android.synthetic.main.add_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddFragment : Fragment() {

    lateinit var gidLayout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        createPosts()

        return  inflater.inflate(R.layout.add_layout,container ,false)

    }

    private fun createPosts() : Unit {

        ClientIterceptorAdd.getUserAdd.getAddPost()
            .enqueue(object : Callback<List<AddPostResponseBean>> {
                override fun onFailure(call: Call<List<AddPostResponseBean>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<AddPostResponseBean>>,
                    response: Response<List<AddPostResponseBean>>
                ) {
                    createAddPosts(resizeImage(response))
                }
            })
    }

    private fun createAddPosts(listFiltered: List<AddPostResponseBean>): RecyclerView {

        gidLayout = GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL, false)
        addPost.layoutManager = gidLayout
        val adapterAddPost = AddPostStoryAdapter(listFiltered)
        addPost.adapter = adapterAddPost

        return addPost
    }

    private fun resizeImage(response: Response<List<AddPostResponseBean>>): MutableList<AddPostResponseBean> {

        if(SessionAddFragmentData.urlImage.size == 30){
            SessionAddFragmentData.urlImage.clear()
        }

        var lista: MutableList<AddPostResponseBean>? = ArrayList<AddPostResponseBean>()

        for (items in response.body()!!) {

            SessionAddFragmentData.urlImage.add(items.download_url)
            items.download_url = "https://picsum.photos/id/${items.id}/400/400"
            lista!!.add(items)
        }

        return lista!!
    }


}
