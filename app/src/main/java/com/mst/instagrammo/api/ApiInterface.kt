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

    //home stories
    @GET("followers.php/{profiloUtente}")
    fun getStories(@Path("profiloUtente") profileId: Int) : Call<StoriesResponse>

    //home posts
    @GET("posts.php")
    fun getHomePosts() : Call<HomePostsResponse>

    //profile info profile
    @GET("profiles.php/{profiloUtente}")
    fun getProfile(@Path("profiloUtente") profileId: Int) : Call<ProfileResponse>

    //profile posts
    @GET("posts.php/{profiloUtente}")
    fun getProfilePosts(@Path("profiloUtente") profileId: Int) : Call<ProfilePostsResponse>

}