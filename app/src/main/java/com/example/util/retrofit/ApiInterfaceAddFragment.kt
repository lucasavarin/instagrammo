package com.example.util.retrofit

import com.example.bean.buissnes.AddPostResponseBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaceAddFragment {

    @GET(value="/v2/list")
    fun getAddPost(@Query("page") page: Int = 1,
                   @Query("limit") limit: Int = 30)
                   : Call<List<AddPostResponseBean>>

}