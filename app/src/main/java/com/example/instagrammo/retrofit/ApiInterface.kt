package com.example.instagrammo.retrofit

import com.example.instagrammo.ResponseStories
import com.example.instagrammo.beans.request.AuthRequest
import com.example.instagrammo.beans.response.AuthResponse
import com.example.instagrammo.beans.response.HomeWrapperPostBean
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST(value = "auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @GET(value = "followers.php/{profiloUtente}")
    fun getStoriesList(@Path ("profiloUtente") id : String): Call<ResponseStories>

    @GET(value = "posts.php")
    fun getPosts(): Call<HomeWrapperPostBean>


}