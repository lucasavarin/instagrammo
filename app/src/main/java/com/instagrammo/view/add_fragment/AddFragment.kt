package com.instagrammo.view.add_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.instagrammo.bean.buissnes.AddPostResponseBean
import com.example.login.R
import com.instagrammo.util.retrofit.ClientIterceptorAdd
import kotlinx.android.synthetic.main.add_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddFragment : Fragment() {

    private lateinit var girdLayout: GridLayoutManager
    private var postList : MutableList<AddPostResponseBean> =  arrayListOf()
    private lateinit var adapterAddPost : AddPostStoryAdapter

    companion object {

        fun newInstance(): AddFragment {
            val addFragment = AddFragment()
            return addFragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_layout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        girdLayout = GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL, false)

        addPost.layoutManager = girdLayout



        adapterAddPost  = AddPostStoryAdapter(arrayListOf())

        adapterAddPost.callBackOnHolderClickListener {
            val secondFragment : Fragment = AddSecondFragment.makeInstance(it)
            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,secondFragment).addToBackStack(null).commit()
        }

        adapterAddPost.setOnAddPostScrollListener { getImage() }
        addPost.adapter = adapterAddPost

        getImage()

    }

    private fun getImage() {

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
                    postList.addAll(resizeImage(response))
                    adapterAddPost.dataList = postList
                    adapterAddPost.notifyDataSetChanged()
                }
            })
    }

    private fun resizeImage(response: Response<List<AddPostResponseBean>>): MutableList<AddPostResponseBean> {

        val listFiltered: MutableList<AddPostResponseBean> = arrayListOf()

        for (items in response.body()!!) {

                items.url = items.download_url
                items.download_url = "https://picsum.photos/id/${items.id}/400/400"

                listFiltered.add(items)
        }

        return listFiltered
    }


}

