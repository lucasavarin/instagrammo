package com.example.instagrammo.retrofit

import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.response.AuthResponse
import com.example.instagrammo.beans.response.FollowersWrapper
import com.example.instagrammo.beans.response.PostsWrapper
import com.example.instagrammo.util.Session
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("auth.php")
    fun doAuth(
        @Body authRequest: AuthRequest
    ): Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getFollowers(
        @Path("profiloUtente") profileId:Int = Session.profileId
    ):Call<FollowersWrapper>

    @GET("posts.php")
    fun getPosts():Call<PostsWrapper>
}