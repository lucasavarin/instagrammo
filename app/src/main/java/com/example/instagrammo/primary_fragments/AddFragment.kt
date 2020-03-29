package com.example.instagrammo.primary_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.util.AddPostData
import com.example.instagrammo.R
import com.example.instagrammo.beans.response.AddPostResponse
import com.example.instagrammo.recyclerview.AddPAdapter
import com.example.instagrammo.retrofit.AddPostClient
import com.example.instagrammo.retrofit.Client
import com.example.instagrammo.secondary_fragments.AddPostDetailFragment
import kotlinx.android.synthetic.main.fragment_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AddFragment: Fragment() {

    private lateinit var gridL : GridLayoutManager
    private lateinit var adpterAdd : AddPAdapter
    private var listaPost : MutableList<AddPostResponse> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    companion object{
        fun makeInstance():Fragment {
            return AddFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridL = GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL, false)
        grid_recycle.layoutManager = gridL

        adpterAdd = AddPAdapter(arrayListOf())
        adpterAdd.callBackOnItemClickListener {
            val nextFragment : Fragment = AddPostDetailFragment.makeInstance(it)
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, nextFragment)
                .addToBackStack(null)
                .commit()
        }

        grid_recycle.adapter = adpterAdd
        getPostfromFragment()

    }

    private fun getPostfromFragment(){
        AddPostClient.getAddClient.getAddPost().enqueue(object : Callback<List<AddPostResponse>>{
            override fun onFailure(call: Call<List<AddPostResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<AddPostResponse>>,
                response: Response<List<AddPostResponse>>
            ) {
                listaPost.addAll(resizePost(response))
                adpterAdd.postData = listaPost
                adpterAdd.notifyDataSetChanged()
            }
        })
    }

    private fun resizePost(response : Response<List<AddPostResponse>>) : MutableList<AddPostResponse>{
        val listaPost : MutableList<AddPostResponse> = arrayListOf()
        val width = 400
        val height = 400

        for (i : AddPostResponse in response.body()!!){
            i.url = i.download_url
            i.download_url = "https://picsum.photos/id/${i.id}/$width/$height"
            listaPost.add(i)
        }
        return listaPost
    }
}