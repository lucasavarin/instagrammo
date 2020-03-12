package thushyanthan.scott.javalynx.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.*
import java.util.ArrayList

class HomeFragment: Fragment() {
    var posts: List<PostPayload> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home,container,false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homePostsListLayout.layoutManager = LinearLayoutManager(context)
        //getPosts()
        val postsCall = ApiClient.getClient.requestPosts()

        postsCall.enqueue(object : Callback<PostsResponse>{
            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                Toast.makeText(activity, "Error1", Toast.LENGTH_SHORT).show()
            }


            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if(response.isSuccessful){
                    val resultBody = response.body()!!
                    if(resultBody.result){
                        posts = resultBody.payload
                        homePostsListLayout.adapter = HomeAdapter(posts,context!!)
                        homePostsListLayout.adapter?.notifyDataSetChanged()
                    }
                }else
                    Toast.makeText(activity, "Error2", Toast.LENGTH_SHORT).show()

            }
        })

    }

    fun getPosts(){
        val postsCall = ApiClient.getClient.requestPosts()

        postsCall.enqueue(object : Callback<PostsResponse>{
            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                Toast.makeText(activity, "Error1", Toast.LENGTH_SHORT).show()
            }


            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                if(response.isSuccessful){
                    val resultBody = response.body()!!
                    if(resultBody.result){
                        posts = resultBody.payload
                        homePostsListLayout.adapter = HomeAdapter(posts,context!!)
                        homePostsListLayout.adapter?.notifyDataSetChanged()
                    }
                }else
                    Toast.makeText(activity, "Error2", Toast.LENGTH_SHORT).show()

            }
        })
    }










}