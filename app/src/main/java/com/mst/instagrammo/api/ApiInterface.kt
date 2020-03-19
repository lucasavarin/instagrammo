package com.mst.instagrammo.api

import com.mst.instagrammo.model.AuthRequest
import com.mst.instagrammo.model.AuthResponse
import com.mst.instagrammo.model.StoriesRequest
import com.mst.instagrammo.model.StoriesResponse
import com.mst.instagrammo.utilities.Session
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST ("auth.php")
    fun doAuth(@Body authRequest : AuthRequest) : Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getStories(@Path("profiloUtente") id:Int) : Call<StoriesResponse>

//    @GET(value = "posts.php")
//    fun getPosts(): Call<PostsResponse>

}