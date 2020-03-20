package com.example.instagrammo.retrofit

import com.example.instagrammo.beans.response.AddPostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaceAdd {
    @GET(value = "/v2/list")
    fun getAddPost(@Query(value = "page") page : Int = 1,
                   @Query(value = "limit") limit : Int = 30) : Call<List<AddPostResponse>>
}