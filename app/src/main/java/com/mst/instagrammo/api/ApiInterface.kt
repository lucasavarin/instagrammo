package com.mst.instagrammo.api

import com.mst.instagrammo.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST ("auth.php")
    fun doAuth(@Body authRequest: AuthRequest) : Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getStories(@Path("profiloUtente") id: Int) : Call<StoriesResponse>

    @GET("posts.php")
    fun getPosts() : Call<PostsResponse>

    @GET("profiles.php/{profiloUtente}")
    fun getProfile(@Path("profiloUtente") id: Int) : Call<ProfileResponse>
}