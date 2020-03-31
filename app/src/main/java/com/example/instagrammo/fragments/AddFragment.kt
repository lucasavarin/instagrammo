package com.example.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.activities.MainActivity
import com.example.instagrammo.adapters.AddGridRecyclerAdapter
import com.example.instagrammo.beans.business.AddPost
import com.example.instagrammo.beans.response.AddPostResponseBeanREST
import com.example.instagrammo.retrofit.RetrofitControllerPicsum
import com.example.instagrammo.util.DOWNLOAD_URL
import com.example.instagrammo.util.DOWNLOAD_URL_REFORMED
import com.example.instagrammo.util.replaceFragment
import kotlinx.android.synthetic.main.fragment_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddFragment: Fragment() {

    val imagesMutable:MutableList<AddPost> = ArrayList()

    var adapter = AddGridRecyclerAdapter(imagesMutable)

    var pageCount = 1

    val lm:GridLayoutManager = GridLayoutManager(context, 3)

    companion object{
        fun makeInstance():AddFragment {
            return AddFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnImageClickedListener { addPost ->
            val activity = activity as MainActivity
            val fragment = FullScreenImageFragment.makeInstance(addPost.downloadUrl, addPost.downloadUrlReformed)
            activity.replaceFragment(fragment, R.id.container, "")
        }
        add_grid.adapter = adapter
        add_grid.layoutManager = lm

        performCall(pageCount)



        add_grid.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView!!, dx, dy)
                val visibleItemCount: Int = lm.childCount
                val totalItemCount: Int = lm.itemCount
                val firstVisibleItemPosition: Int = lm.findFirstVisibleItemPosition()
                // Load more if we have reach the end to the recyclerView
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMoreItems()
                }
            }
        })
    }

    private fun loadMoreItems() {
        pageCount++
        performCall(pageCount)

    }

    private fun performCall(pageNum:Int?){
        val call = RetrofitControllerPicsum.getClient.getAddList(30, pageNum)

        call.enqueue(object: Callback<List<AddPostResponseBeanREST>>{
            override fun onFailure(call: Call<List<AddPostResponseBeanREST>>, t: Throwable) {
                Toast.makeText(context, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<AddPostResponseBeanREST>>,
                response: Response<List<AddPostResponseBeanREST>>
            ) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        for(image in response.body()!!){
                            imagesMutable.add(AddPost(image.downloadUrl, reformDownloadUrl(image.downloadUrl)))
                        }
                        add_grid.adapter?.notifyItemRangeChanged(add_grid.adapter?.itemCount!!, imagesMutable.size-1)
                    } else {
                        Toast.makeText(context, "Nessuna immagine trovata", Toast.LENGTH_SHORT).show()
                    }
                } else{
                    Toast.makeText(context, "Errore di comunicazione", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun reformDownloadUrl(downloadUrl:String):String{
        val split = downloadUrl.split("/")
        var final = ""
        for(index in 0..split.size-3){
            final += split[index] + "/"
        }
        final = "${final}400/400"
        return final
    }
}