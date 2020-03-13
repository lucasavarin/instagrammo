package com.example.instagrammo


import model.AuthResponse
import model.HomeWrapperPostBean
import model.Session
import model.StoriesResponse
import model.User
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface{

    @POST("auth.php")
    fun auth(
        @Body user: User
    ): Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getStoriesList(@Path("profiloUtente") id:String) : Call<StoriesResponse>

    @GET(value = "posts.php")
    fun getFollowerPost() : Call<HomeWrapperPostBean>
}