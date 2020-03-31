package com.example.view.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bean.buissnes.AddPostResponseBean
import com.example.bean.buissnes.HomePayloadPostBean
import com.example.bean.buissnes.HomeWrapperPostBean
import com.example.bean.buissnes.HomeWrapperResponse
import com.example.login.R
import com.example.util.retrofit.ClientInterceptor
import com.example.util.retrofit.ClientIterceptorAdd
import kotlinx.android.synthetic.main.add_layout.*
import kotlinx.android.synthetic.main.add_layout_click_item.*
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.android.synthetic.main.profile_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread


class HomeFragment : Fragment() {

    val lista: MutableList<HomePayloadPostBean>? = ArrayList<HomePayloadPostBean>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        ClientInterceptor.getUser.getFollowers().enqueue(object : Callback<HomeWrapperResponse>{

            override fun onFailure(call: Call<HomeWrapperResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<HomeWrapperResponse>,
                response: Response<HomeWrapperResponse>
            ) {
                createFollowerStories(response)
            }
        })
        ClientInterceptor.getUser.getFollowerPost().enqueue(object : Callback<HomeWrapperPostBean>{
            override fun onFailure(call: Call<HomeWrapperPostBean>, t: Throwable) {

            }



            override fun onResponse(
                call: Call<HomeWrapperPostBean>,
                response: Response<HomeWrapperPostBean>
            ) {
                if (lista != null) {
                    createHomePost()
                }
                createFollowerPost(response)
            }

        })

        return inflater.inflate(R.layout.home_layout,container,false)
    }


    private fun createHomePost(){
        ClientInterceptor.getUser.getProceduralPosts(0, 15).enqueue(object : Callback<List<HomePayloadPostBean>>{
            override fun onFailure(call: Call<List<HomePayloadPostBean>>, t: Throwable) {

            }



            override fun onResponse(
                call: Call<List<HomePayloadPostBean>>,
                response: Response<List<HomePayloadPostBean>>
            ) {
                if (lista != null) {
                    lista.addAll(doCollect(response))
                }

            }

        })
    }


//    private fun createPosts(list : MutableList<HomeWrapperPostBean>) {
//
//        ClientInterceptor.getUser.getProceduralPosts(0, 15).enqueue(object : Callback<List<HomePayloadPostBean>> {
//                override fun onFailure(call: Call<List<HomePayloadPostBean>>, t: Throwable) {
//                    Toast.makeText(
//                        context,
//                        "Impossibile caricare le immagini",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//
//                override fun onResponse(
//                    call: Call<List<HomeWrapperPostBean>>,
//                    response: Response<List<HomeWrapperPostBean>>
//                ) {
//
//                    list.addAll(list)
//                    createFollowerPost(list);
//                }
//            })
//    }
//
    private fun doCollect(response: Response<List<HomePayloadPostBean>>): MutableList<HomePayloadPostBean>{
        val lista: MutableList<HomePayloadPostBean> = arrayListOf()
    for (items in response.body()!!){
        lista!!.add(items)
        }
    return lista
    }
//

    private fun createFollowerStories(response: Response<HomeWrapperResponse>) : RecyclerView {

        val linearLayoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        HomeFollowerStory.layoutManager = linearLayoutManager
        HomeFollowerStory.setHasFixedSize(true)
        val adapterFollowers =
            HomeFollowerStoryAdapter(response.body()!!.payload)
        HomeFollowerStory.adapter = adapterFollowers

        return HomeFollowerStory
    }

    private fun createFollowerPost(response : Response<HomeWrapperPostBean>) : RecyclerView {

        val lista: MutableList<HomePayloadPostBean>? = ArrayList<HomePayloadPostBean>()

        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.stackFromEnd = true
        linearLayoutManager.reverseLayout = true
        HomeFollowerPosts.layoutManager = linearLayoutManager
        HomeFollowerPosts.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        val adapterFollowerPost = HomeFollowerPostAdapter(response.body()!!.payload)
        adapterFollowerPost.notifyDataSetChanged()
        HomeFollowerPosts.adapter = adapterFollowerPost
        adapterFollowerPost.setOnLastItemsCallback {

            for (items in response.body()!!.payload) {
                lista!!.add(items)
            }

        }
        return HomeFollowerPosts
    }





}