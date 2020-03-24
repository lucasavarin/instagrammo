package com.example.instagrammo.ui.fragment

import GridItemDecoration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instagrammo.R
import com.example.instagrammo.adapter.AddPostAdapter
import com.example.instagrammo.model.AddPostResponseBean
import com.example.instagrammo.model.AddResponseBeanApplicativo
import com.example.instagrammo.retrofit.RetrofitController
import kotlinx.android.synthetic.main.add_fragment_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class AddFragment : Fragment(){

    lateinit var gidLayout: GridLayoutManager

    companion object {

        fun newInstance(): AddFragment {
            val fragment = AddFragment()

            return fragment
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
        return inflater.inflate(R.layout.add_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayout = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        rAdd.layoutManager = gridLayout

        val retrofit = RetrofitController.getIstance()
        val list = arrayListOf<AddResponseBeanApplicativo>()
        val random = Random(10)
        retrofit.getPosts(random.nextInt().toString()).enqueue(object :Callback<List<AddPostResponseBean>>{
            override fun onFailure(call: Call<List<AddPostResponseBean>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<List<AddPostResponseBean>>,
                response: Response<List<AddPostResponseBean>>
            ) {

                if(response.isSuccessful){

                    response.body()?.forEach{
                        val nuovoUrlArray = it.dUrl.split("/")
                        val nuovoUrl = nuovoUrlArray.mapIndexed lt@{ i,e ->
                            if(i == nuovoUrlArray.size-1 || i == nuovoUrlArray.size-2)
                                return@lt "400"
                            else
                                return@lt e
                        }.joinToString ("/")
                        val item = AddResponseBeanApplicativo(it.dUrl,nuovoUrl,it.autore)
                        Log.d("nuovoUrl",nuovoUrl)
                        list.add(item)
                    }
                    rAdd.adapter = AddPostAdapter(list)
                    rAdd.addItemDecoration(GridItemDecoration(10,3))
                    (rAdd.adapter as AddPostAdapter).setCallBack {item ->fragmentManager!!
                        .beginTransaction().add(R.id.frame,InsertPost.newInstance(item)).remove(this@AddFragment).commit()}
                }
            }
        })
    }
}